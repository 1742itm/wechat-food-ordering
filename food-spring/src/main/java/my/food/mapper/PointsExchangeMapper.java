package my.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.PointsExchange;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointsExchangeMapper extends BaseMapper<PointsExchange> {
    List<PointsExchange> selectByUserId(Integer userId);
}