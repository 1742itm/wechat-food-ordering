package my.food.mapper;

import my.food.entity.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
    @Select("<script>"+
            "select * from food where status=#{status} AND id in "+
            " <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> " +
            "  #{item} " +
            " </foreach> " +
            "</script>"
    )
    List<Food> selectByIdsAndStatus(@Param("ids")List<Integer> ids, @Param("status")Integer status);
}
