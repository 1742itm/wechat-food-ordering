package my.food.mapper;

import my.food.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("SELECT * FROM category order by sort asc")
    List<Category> selectAllOrderBySortAsc();
}
