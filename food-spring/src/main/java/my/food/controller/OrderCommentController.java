package my.food.controller;

import my.food.bean.OrderDtoList;
import my.food.entity.OrderComment;
import my.food.entity.User;
import my.food.entity.dto.OrderDto;
import my.food.service.IFoodService;
import my.food.service.IOrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
public class OrderCommentController {

    @Autowired
    private IOrderCommentService orderCommentService;

    @Autowired
    private IFoodService foodService;

    @PostMapping("/submitComment")
    public Map<String, Object> submitComment(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            Integer orderId = Integer.valueOf(params.get("orderId").toString());
            Integer star = Integer.valueOf(params.get("star").toString());
            String content = (String) params.get("content");
            
            User user = (User) request.getSession().getAttribute("user");
            
            if (user == null) {
                result.put("success", false);
                result.put("message", "请先登录");
                return result;
            }
            
            boolean success = orderCommentService.saveOrUpdateComment(orderId, user.getId(), star, content);
            
            if (success) {
                result.put("success", true);
                result.put("message", "评价成功");
            } else {
                result.put("success", false);
                result.put("message", "评价失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "服务器异常: " + e.getMessage());
        }
        
        return result;
    }

    @GetMapping("/getOrderListWithComment")
    public Map<String, Object> getOrderListWithComment(HttpServletRequest request) {
        OrderDtoList orderDtoList = foodService.record(request);
        
        if (orderDtoList != null && orderDtoList.getList() != null) {
            List<OrderDto> orderList = orderDtoList.getList();
            
            for (OrderDto order : orderList) {
                OrderComment comment = orderCommentService.getByOrderId(order.getId());
                if (comment != null) {
                    Map<String, Object> commentMap = new HashMap<>();
                    commentMap.put("star", comment.getStar());
                    commentMap.put("content", comment.getContent());
                    commentMap.put("createTime", comment.getCreateTime());
                    order.setCommentInfo(commentMap);
                    order.setHasComment(true);
                } else {
                    order.setHasComment(false);
                }
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", orderDtoList != null ? orderDtoList.getList() : null);
        return result;
    }
}