package my.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.OrderComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderCommentMapper extends BaseMapper<OrderComment> {

    OrderComment selectByOrderId(@Param("orderId") Integer orderId);

    List<OrderComment> selectByUserId(@Param("userId") Integer userId);
}