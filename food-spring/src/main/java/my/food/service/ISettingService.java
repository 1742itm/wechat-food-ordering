package my.food.service;

import my.food.entity.dto.SettingDto;
import my.food.entity.Setting;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISettingService extends IService<Setting> {
    public SettingDto getSettingDto();
}
