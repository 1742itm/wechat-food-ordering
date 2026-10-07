package my.food.service.impl;

import my.food.entity.dto.SettingDto;
import my.food.entity.Setting;
import my.food.mapper.SettingMapper;
import my.food.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {
    @Autowired
    private SettingMapper settingMapper;
    @Value("${host}")
    private String host;//注入application中的host

    public SettingDto getSettingDto(){
        //list()方法为Mybatis-plus从Mybatis进行扩展的功能，表示查询setting表的全部记录
        List<Setting> settings= this.list();
        return SettingDto.toSettingDto(settings,host);
    }
}
