package my.food.mapper;

import my.food.entity.OrderFood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderFoodMapper extends BaseMapper<OrderFood> {
    void insertBatch(List<OrderFood> orderFoods);

    @Select("select * from order_food where order_id=#{orderId}")
    List<OrderFood> selectByOrderId(Integer orderId);

    @Select("select food_id from order_food where order_id=#{orderId} limit 0,1")
    Integer selectFoodIdById(Integer orderId);


}
