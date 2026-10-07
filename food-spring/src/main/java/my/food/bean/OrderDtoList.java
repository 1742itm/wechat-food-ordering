package my.food.bean;

import lombok.Data;
import my.food.entity.dto.OrderDto;

import java.util.List;

@Data
public class OrderDtoList {
    private Integer last_id;
    private List<OrderDto> list;
}
