package my.food.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.food.bean.OrderDtoList;
import my.food.bean.Param;
import my.food.bean.Result;
import my.food.entity.dto.OrderDto;
import my.food.entity.dto.SettingDto;
import my.food.entity.User;
import my.food.service.IFoodService;
import my.food.service.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private ISettingService settingService;
    @Autowired
    private IFoodService foodService;

    @GetMapping("/index")
    public SettingDto index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return new SettingDto();
        }
        SettingDto settingDto=new SettingDto();
        try{
            System.out.println(request.getCookies());
            settingDto=settingService.getSettingDto();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  settingDto;
    }

    @GetMapping("/list")
    public Result list() {
        Result result=new Result();
        try{
            result=foodService.list1();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody Param param, HttpServletRequest request) {
        Result result=new Result();
        try{
            result=foodService.createOrder(param.getOrder(),request);
            System.out.println(param.getOrder());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    @PostMapping("/getOrderById")
    public OrderDto getOrderById(@RequestBody Param param, HttpServletRequest request){
        OrderDto orderDto=new OrderDto();
        try{
            orderDto=foodService.getOrderById(param.getId(),request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  orderDto;

    }
    @PostMapping("/commentOrder")
    public Result commentOrder(@RequestBody Param param){
        Result result=new Result();
        try{
            result=foodService.commentOrder(param.getId(),param.getComment());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
    @PostMapping("/pay")
    public Result pay(@RequestBody Param param,HttpServletRequest request){
        Result result=new Result();
        try{
            result=foodService.pay(param.getId(),request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }


    @GetMapping("/orderlist")
    public OrderDtoList orderlist( Param param,HttpServletRequest request){
        OrderDtoList orderDtoList=new OrderDtoList();
        try{
            orderDtoList =foodService.orderlist(param.getLast_id(),param.getRow(),request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  orderDtoList;
    }

    @GetMapping("/record")
    public OrderDtoList record( HttpServletRequest request){
        OrderDtoList orderDtoList=new OrderDtoList();
        try{
            orderDtoList =foodService.record(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  orderDtoList;
    }
}
