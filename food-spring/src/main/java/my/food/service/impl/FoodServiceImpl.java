package my.food.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.food.bean.OrderDtoList;
import my.food.bean.Promotion;
import my.food.bean.Result;
import my.food.entity.*;
import my.food.entity.dto.*;
import my.food.mapper.*;
import my.food.service.IFoodService;
import my.food.service.IPointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.food.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderFoodMapper orderFoodMapper;
    @Value("${host}")
    private String host;//注入application中的host
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IPointsService pointsService;

    public Result list1(){
        Result result=new Result();

        List<Category> categories=categoryMapper.selectAllOrderBySortAsc();

        result.setList(CategoryDto.entityToDtoList(categories,this.selectAll()));
        List<Setting> settings= settingMapper.selectAll();
        SettingDto settingDto=SettingDto.toSettingDto(settings,host);

        Gson gson=new Gson();
        Type listType = new TypeToken<List<Promotion>>() {}.getType();
        String json=settingDto.getPromotion();
        List<Promotion> promotions=gson.fromJson(json,listType);

        result.setPromotion(promotions);
        return result;
    }

    @Transactional
    public Result createOrder(List<FoodDto> order, HttpServletRequest request){
        Result result=new Result();

        Order newOrder=new Order();
        List<OrderFood> orderFoods=new ArrayList<>();
        order.forEach(o->{
            if(o!=null) {
                OrderFood orderFood = new OrderFood();
                orderFood.setFoodId(o.getId());
                orderFood.setNumber(o.getNumber());
                orderFood.setPrice(o.getPrice());
                orderFoods.add(orderFood);
                newOrder.setPrice(newOrder.getPrice() + o.getPrice() * o.getNumber());
                newOrder.setNumber(newOrder.getNumber() + o.getNumber());
            }
        });

        List<Setting> settings= settingMapper.selectAll();
        SettingDto settingDto=SettingDto.toSettingDto(settings,host);
        Gson gson=new Gson();
        Type listType = new TypeToken<List<Promotion>>() {}.getType();
        List<Promotion> promotions=gson.fromJson(settingDto.getPromotion(),listType);
        Promotion promotion=new Promotion();
        promotion.setPromotionDiff(0.0); promotion.setPromotionPrice(0.0);
        promotions.forEach(p->{
            Double promotionDiff2=newOrder.getPrice()-p.getK();
            if(promotionDiff2>0 && promotionDiff2>promotion.getPromotionDiff())
                promotion.setPromotionPrice(p.getV().doubleValue());
            else
                promotion.setPromotionDiff(promotionDiff2);
        });

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        newOrder.setUserId(user.getId());

        newOrder.setPrice(newOrder.getPrice()-promotion.getPromotionPrice());
        newOrder.setComment("");
        orderMapper.insert(newOrder);

        orderFoods.forEach(o->{
            o.setOrderId(newOrder.getId());
        });

        orderFoodMapper.insertBatch(orderFoods);
        result.setOrder_id(newOrder.getId());
        return result;
    }

    public OrderDto getOrderById(Integer orderId, HttpServletRequest request){
        OrderDto orderDto=new OrderDto();
        Order order=orderMapper.selectById(orderId);

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");

        if(order.getUserId()==null || !order.getUserId().equals(user.getId()) )
            return orderDto;

        orderDto=OrderDto.entityToDto(order);
        orderDto.setSn("WX"+Tool.padString(order.getId().toString(),14,'0',true));
        orderDto.setCode("A"+Tool.padString(order.getId().toString(),2,'0',true));
        orderDto.setIs_taken(order.getIsTaken()==0?false:true);
        orderDto.setIs_pay(order.getIsPay()==0?false:true);

        List<FoodDto> order_food=new ArrayList<>();
        List<OrderFood> orderFoods= orderFoodMapper.selectByOrderId( order.getId());
        orderFoods.forEach(orderFood -> {
            Food food=foodMapper.selectById(orderFood.getFoodId());
            if(food.getStatus().equals(1)){
                FoodDto foodDto=FoodDto.entityToDto(food);
                foodDto.setImage_url(host+"/"+food.getImageUrl());
                foodDto.setNumber(orderFood.getNumber());
                order_food.add(foodDto);
            }
        });

        orderDto.setOrder_food(order_food);
        orderDto.setId(order.getId());
        return orderDto;
    }

    public Result commentOrder(Integer orderId,String comment){
        Result result=new Result();
        Order order=orderMapper.selectById(orderId);
        if(order==null || order.getIsPay().equals(1)) {
            result.setMessage("订单备注添加失败");
            return result;
        }

        order.setComment(comment);
        orderMapper.updateById(order);
        result.setMessage("订单备注添加成功");
        return result;
    }

    @Transactional
    public Result pay(Integer orderId,HttpServletRequest request){
        Result result=new Result();
        Order order=orderMapper.selectById(orderId);

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(order.getUserId()==null || !order.getUserId().equals(user.getId()) ) {
            result.setMessage("支付失败");
            return result;
        }

        order.setIsPay(1);
        order.setPayTime(LocalDateTime.now());
        orderMapper.updateById(order);

        user=userMapper.selectById(user.getId());
        user.setPrice(user.getPrice()+order.getPrice());
        userMapper.updateById(user);

        int points = (int) Math.floor(order.getPrice());
        pointsService.addPoints(user.getId(), points, "消费获得积分");

        result.setMessage("支付成功");
        return result;
    }


    public OrderDtoList orderlist(Integer last_id, Integer row,HttpServletRequest request){
        Result result=new Result();

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");

        List<Order> orders=orderMapper.selectAPage(last_id,user.getId(),row);
        if(last_id>0)
            orders=orderMapper.selectAPage(last_id,user.getId(),row);
        else
            orders=orderMapper.selectAPage1( user.getId(), row);

        OrderDtoList orderDtoList=new OrderDtoList();
        if(orders.size()==0)
            orderDtoList.setLast_id(0);
        else
            orderDtoList.setLast_id(orders.get(orders.size()-1).getId());

        List<OrderDto> orderDtos=OrderDto.entityToDtoList(orders);

        orderDtos.forEach(order -> {
            Integer foodId=orderFoodMapper.selectFoodIdById(order.getId());
            Food food=foodMapper.selectById(foodId);
            order.setFirst_food_name(food.getName());
        });

        orderDtoList.setList(orderDtos);
        return orderDtoList;
    }

    public OrderDtoList record(HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");

        List<Order> orders=orderMapper.selectByUserIdAndPayedOrderByIdDESC(user.getId());
        List<OrderDto> orderDtos=OrderDto.entityToDtoList(orders);

        orderDtos.forEach(order -> {
            order.setSn("WX"+Tool.padString(order.getId().toString(),14,'0',true));
            order.setCode("A"+Tool.padString(order.getId().toString(),2,'0',true));
            order.setIs_taken(order.getIsTaken()==0?false:true);
            order.setIs_pay(order.getIsPay()==0?false:true);
            
            List<FoodDto> order_food=new ArrayList<>();
            List<OrderFood> orderFoods= orderFoodMapper.selectByOrderId(order.getId());
            orderFoods.forEach(orderFood -> {
                Food food=foodMapper.selectById(orderFood.getFoodId());
                if(food.getStatus().equals(1)){
                    FoodDto foodDto=FoodDto.entityToDto(food);
                    foodDto.setImage_url(host+"/"+food.getImageUrl());
                    foodDto.setNumber(orderFood.getNumber());
                    order_food.add(foodDto);
                }
            });
            order.setOrder_food(order_food);
        });

        OrderDtoList orderDtoList=new OrderDtoList();
        orderDtoList.setList(orderDtos);
        return orderDtoList;
    }



    public List<Food> selectAll(){
        List<Food> foods=this.list();
        for (int i = 0; i < foods.size(); i++) {
            foods.get(i).setImageUrl(host+"/"+foods.get(i).getImageUrl());
        }
        return foods;
    }
}
