package my.food.bean;

import lombok.Data;
import my.food.entity.dto.FoodDto;

import java.util.List;

@Data
public class Param {
    private Integer orderId;
    private String code;
    private String token;
    private String js_code;
    List<FoodDto> order;
    private Integer id;
    private String comment;

    private Integer last_id;
    private Integer row;

}
