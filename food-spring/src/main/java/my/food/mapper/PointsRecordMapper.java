package my.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.PointsRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointsRecordMapper extends BaseMapper<PointsRecord> {
    List<PointsRecord> selectByUserIdOrderByCreatedAtDesc(Integer userId);
}