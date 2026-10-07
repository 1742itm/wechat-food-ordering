package my.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.Points;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PointsMapper extends BaseMapper<Points> {
    @Update("UPDATE points SET total_points = total_points + #{points}, available_points = available_points + #{points} WHERE user_id = #{userId}")
    int addPoints(@Param("userId") Integer userId, @Param("points") Integer points);

    @Update("UPDATE points SET available_points = available_points - #{points} WHERE user_id = #{userId} AND available_points >= #{points}")
    int deductPoints(@Param("userId") Integer userId, @Param("points") Integer points);
}