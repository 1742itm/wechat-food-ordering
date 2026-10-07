package my.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import my.food.bean.Result;
import my.food.entity.OrderFood;

public interface IOrderFoodService1  extends IService<OrderFood> {
    public Result selectByOrderId1(Integer orderId);

}
