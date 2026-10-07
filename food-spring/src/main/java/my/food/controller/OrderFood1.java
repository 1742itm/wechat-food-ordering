package my.food.controller;

import my.food.bean.Param;
import my.food.bean.Result;
import my.food.service.IOrderFoodService;
import my.food.service.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-food1")
public class OrderFood1 {
    @Autowired
    private IOrderFoodService orderFoodService;


    @PostMapping("/selectByOrderId")
    public Result selectByOrderId(@RequestBody Param param){
        Result result=new Result();
        try{
            result=orderFoodService.selectByOrderId(param.getOrderId());

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

}
