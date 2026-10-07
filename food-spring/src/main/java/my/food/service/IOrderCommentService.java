package my.food.service;

import my.food.entity.OrderComment;

public interface IOrderCommentService {

    OrderComment getByOrderId(Integer orderId);

    boolean saveOrUpdateComment(Integer orderId, Integer userId, Integer star, String content);
}