package my.food.service;

import my.food.bean.Result;
import my.food.entity.OrderFood;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2024-04-16
 */
public interface IOrderFoodService extends IService<OrderFood> {
    Result selectByOrderId(Integer orderId);
}
