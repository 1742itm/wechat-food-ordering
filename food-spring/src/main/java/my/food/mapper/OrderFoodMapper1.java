package my.food.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.Food;
import my.food.entity.Order;
import my.food.entity.OrderFood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderFoodMapper1 extends BaseMapper<OrderFood> {
    @Select("select * from order_food where order_id=#{orderId}")
    List<OrderFood> selectByOrderId1(@Param("orderId")Integer orderId);

}
