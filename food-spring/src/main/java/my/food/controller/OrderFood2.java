package my.food.controller;

import my.food.bean.Param;
import my.food.bean.Result;
import my.food.service.IFoodService;
import my.food.service.IOrderFoodService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order-food2")
public class OrderFood2 {
    @Autowired
    private IOrderFoodService1 orderFoodService1;



    @PostMapping("/selectByOrderId1")
    public Result selectByOrderId1(@RequestBody Param param) {
        Result result=new Result();
        try{
            result=orderFoodService1.selectByOrderId1(param.getOrderId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
}
