package my.food.mapper;

import my.food.entity.Setting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.food.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SettingMapper extends BaseMapper<Setting> {
    @Select("SELECT * FROM setting")
    public List<Setting> selectAll();
}
