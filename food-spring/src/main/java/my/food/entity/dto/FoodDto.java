package my.food.entity.dto;

import lombok.Data;
import my.food.entity.Food;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class FoodDto {
    private String image_url;
    private Integer category_id;

    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private LocalDateTime delete_time;

    private Integer id;
    private String name;
    private Double price;
    private Integer status;
    private Integer number;

    //将DTO对象转为实体类对象
    public static Food dtoToEntity(FoodDto dto) {
        if(dto==null) return null;
        Food entity =new Food();
        //复制同名的属性值
        BeanUtils.copyProperties(dto, entity);
        entity.setImageUrl(dto.getImage_url());
        entity.setCategoryId(dto.getCategory_id());
        entity.setCreateTime(dto.getCreate_time());
        entity.setDeleteTime(dto.getDelete_time());
        entity.setUpdateTime(dto.getUpdate_time());
        return entity;
    }
    //将实体类对象转换为DTO对象
    public static FoodDto entityToDto(Food entity) {
        if(entity==null) return null;
        FoodDto dto = new FoodDto();
        BeanUtils.copyProperties(entity,dto);
        dto.setCategory_id(entity.getCategoryId());
        dto.setImage_url(entity.getImageUrl());
        dto.setCreate_time(entity.getCreateTime());
        dto.setDelete_time(entity.getDeleteTime());
        dto.setUpdate_time(entity.getUpdateTime());
        return dto;
    }

    public static List<Food> dtoToEntityList(List<FoodDto> dtos){
        if(dtos==null) return null;
        List<Food> foods=new ArrayList<>();
        dtos.forEach(dto->foods.add(dtoToEntity(dto)));
        return foods;
    }

    public static List<FoodDto> entityToDtoList(List<Food> entitys){
        if(entitys==null) return null;
        List<FoodDto> foodDtos=new ArrayList<>();
        entitys.forEach(entity->foodDtos.add(entityToDto(entity)));
        return foodDtos;
    }

}
