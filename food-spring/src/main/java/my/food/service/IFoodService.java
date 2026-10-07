package my.food.service;

import jakarta.servlet.http.HttpServletRequest;
import my.food.bean.OrderDtoList;
import my.food.bean.Result;
import my.food.entity.dto.FoodDto;
import my.food.entity.dto.OrderDto;
import my.food.entity.Food;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IFoodService extends IService<Food> {
    List<Food> selectAll();
    Result list1();
    public Result createOrder(List<FoodDto> order, HttpServletRequest request);
    public OrderDto getOrderById(Integer orderId, HttpServletRequest request);

    public Result commentOrder(Integer orderId,String comment);
    public Result pay(Integer orderId,HttpServletRequest request);
    public OrderDtoList orderlist(Integer last_id, Integer row, HttpServletRequest request);
    public OrderDtoList record(HttpServletRequest request);
}
