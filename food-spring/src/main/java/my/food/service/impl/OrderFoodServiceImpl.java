package my.food.service.impl;

import my.food.bean.Result;
import my.food.entity.OrderFood;
import my.food.mapper.OrderFoodMapper;
import my.food.service.IOrderFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2024-04-16
 */
@Service
public class OrderFoodServiceImpl extends ServiceImpl<OrderFoodMapper, OrderFood> implements IOrderFoodService {
    @Autowired
    private OrderFoodMapper orderFoodMapper;

    public Result selectByOrderId(Integer orderId){
        List<OrderFood>  orderFoods =orderFoodMapper.selectByOrderId(orderId);
        Result result=new Result();
        result.setOrderFoods(orderFoods);
        return result;
    }

}
