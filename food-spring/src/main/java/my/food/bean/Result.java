package my.food.bean;

import lombok.Data;
import my.food.entity.dto.CategoryDto;
import my.food.entity.OrderFood;

import java.util.List;

@Data
public class Result {
    private String token;
    private Boolean isLogin;
    private Integer credit;
    private String err;
    //private List<FoodDto> list;
    private List<Promotion> promotion;
    private List<CategoryDto> list;
    private Integer order_id;
    private String sessionId;

    private String message;
    private List<OrderFood> orderFoods;




}
