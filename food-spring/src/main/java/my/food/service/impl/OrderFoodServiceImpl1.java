package my.food.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.food.bean.Result;
import my.food.entity.Food;
import my.food.entity.OrderFood;
import my.food.mapper.FoodMapper;
import my.food.mapper.OrderFoodMapper;
import my.food.mapper.OrderFoodMapper1;
import my.food.service.IFoodService;
import my.food.service.IOrderFoodService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodServiceImpl1 extends ServiceImpl<OrderFoodMapper1, OrderFood> implements IOrderFoodService1 {
    @Autowired
    private OrderFoodMapper1 orderFoodMapper1;

    public Result selectByOrderId1(Integer orderId) {
        Result result=new Result();
        List<OrderFood> orderFoods =orderFoodMapper1.selectByOrderId1(orderId);
        result.setOrderFoods(orderFoods);
        return result;
    }
}
