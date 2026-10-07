package my.food.mapper;

import my.food.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.OrderFood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from order1 where user_id=#{userId} and is_pay=1 order by id DESC")
    List<Order> selectByUserIdAndPayedOrderByIdDESC(@Param("userId")Integer userId);

    @Select("select * from order1 where user_id=#{userId} and id<#{id} order by id desc limit 0, #{row}")
    List<Order> selectAPage(@Param("id")Integer id, @Param("userId")Integer userId, @Param("row")Integer row);

    @Select("select * from order1 where user_id=#{userId}  order by id desc limit 0, #{row}")
    List<Order> selectAPage1( @Param("userId")Integer userId, @Param("row")Integer row);

}
