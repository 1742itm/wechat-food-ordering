package my.food.entity.dto;

import lombok.Data;
import my.food.entity.Category;
import my.food.entity.Food;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private Integer sort;
    private List<FoodDto> food;

    //将实体类对象转换为DTO对象
    public static CategoryDto entityToDto(Category entity, List<Food> food) {
        if(entity==null) return null;
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(entity,dto);

        if(food!=null) {
            List<Food> foods=new ArrayList<>();
            food.forEach(f -> {
                if(entity.getId().equals(f.getCategoryId()))
                    foods.add(f);
            });
            dto.setFood(FoodDto.entityToDtoList(foods));
        }

        return dto;
    }



    public static List<CategoryDto> entityToDtoList(List<Category> entitys, List<Food> food){
        if(entitys==null) return null;
        List<CategoryDto> categoryDto=new ArrayList<>();
        entitys.forEach(entity->categoryDto.add(entityToDto(entity,food)));
        return categoryDto;
    }

}
