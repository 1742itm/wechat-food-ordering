package my.food.entity.dto;

import lombok.Data;
import my.food.entity.Order;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private String sn;
    private String code;
    private Boolean is_taken;
    private Boolean is_pay;

    private Integer userId;
    private Double price;
    private Double promotion;
    private Integer number;
    private String comment;
    private LocalDateTime create_time;
    private LocalDateTime pay_time;
    private LocalDateTime taken_time;
    private Integer isPay;
    private Integer isTaken;

    private List<FoodDto> order_food;

    private Integer id;
    private String first_food_name;
    
    private Boolean hasComment;
    private java.util.Map<String, Object> commentInfo;

    //将实体类对象转换为DTO对象
    public static OrderDto entityToDto(Order entity) {
        if(entity==null) return null;
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(entity,dto);
        dto.setCreate_time(entity.getCreateTime());
        dto.setTaken_time(entity.getTakenTime());
        dto.setPay_time(entity.getPayTime());
        dto.setIs_taken(entity.getIsTaken()>0?true:false);
        dto.setIs_pay(entity.getIsPay()>0?true:false);
        return dto;
    }

    public static List<OrderDto> entityToDtoList(List<Order> entitys){
        if(entitys==null) return null;
        List<OrderDto> orderDtos=new ArrayList<>();
        entitys.forEach(entity->orderDtos.add(entityToDto(entity)));
        return orderDtos;
    }
}
