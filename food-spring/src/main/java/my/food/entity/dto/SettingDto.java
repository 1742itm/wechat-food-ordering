package my.food.entity.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import my.food.entity.Setting;

import java.lang.reflect.Type;
import java.util.List;

@Data
public class SettingDto {
    private String appid;
    private String appsecret;
    private String img_ad;
    private List<String> img_category;
    private List<String> img_swiper;
    private String promotion;

    static public SettingDto toSettingDto(List<Setting> settings,String host){
        SettingDto settingDto=new SettingDto();
        settings.forEach(setting -> {
            if ("appid".equals(setting.getName())){
                settingDto.setAppid(setting.getValue());
            }
        });
        settings.forEach(setting -> {
            if ("appsecret".equals(setting.getName())){
                settingDto.setAppsecret(setting.getValue());
            }
        });
        settings.forEach(setting -> {
            if ("img_ad".equals(setting.getName())){
                settingDto.setImg_ad(host+setting.getValue());
            }
        });
        Gson gson=new Gson();
        settings.forEach(setting -> {
            if ("img_category".equals(setting.getName())){
                //把 JSON 数组 转成 Java 的 List<String> 这种带泛型的集合
                Type listType = new TypeToken<List<String>>() {}.getType();
                List<String> cas=gson.fromJson(setting.getValue(),listType);
                for (int i = 0; i < cas.size(); i++) {
                    cas.set(i,host+cas.get(i));
                }
                settingDto.setImg_category(cas);
            }
        });
        settings.forEach(setting -> {
            if ("img_swiper".equals(setting.getName())){
                Type listType = new TypeToken<List<String>>() {}.getType();
                List<String> cas=gson.fromJson(setting.getValue(),listType);
                for (int i = 0; i < cas.size(); i++) {
                    cas.set(i,host+cas.get(i));
                }
                settingDto.setImg_swiper(cas);
            }
        });
        settings.forEach(setting -> {
            if ("promotion".equals(setting.getName())){
                settingDto.setPromotion(setting.getValue());
            }
        });

        return settingDto;
    }

}
