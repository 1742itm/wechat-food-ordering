package my.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import my.food.entity.OrderComment;
import my.food.mapper.OrderCommentMapper;
import my.food.service.IOrderCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderCommentServiceImpl implements IOrderCommentService {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommentServiceImpl.class);

    @Autowired
    private OrderCommentMapper orderCommentMapper;

    @Override
    public OrderComment getByOrderId(Integer orderId) {
        logger.info("查询订单评价 orderId={}", orderId);
        QueryWrapper<OrderComment> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        OrderComment comment = orderCommentMapper.selectOne(wrapper);
        logger.info("查询结果: {}", comment);
        return comment;
    }

    @Override
    @Transactional
    public boolean saveOrUpdateComment(Integer orderId, Integer userId, Integer star, String content) {
        logger.info("保存评价 orderId={}, userId={}, star={}, content={}", orderId, userId, star, content);
        
        OrderComment existing = getByOrderId(orderId);
        
        if (existing != null) {
            logger.info("更新已有评价 id={}", existing.getId());
            existing.setStar(star);
            existing.setContent(content);
            existing.setUpdateTime(LocalDateTime.now());
            int result = orderCommentMapper.updateById(existing);
            logger.info("更新结果: {}", result > 0 ? "成功" : "失败");
            return result > 0;
        } else {
            logger.info("插入新评价");
            OrderComment comment = new OrderComment();
            comment.setOrderId(orderId);
            comment.setUserId(userId);
            comment.setStar(star);
            comment.setContent(content);
            comment.setCreateTime(LocalDateTime.now());
            comment.setUpdateTime(LocalDateTime.now());
            int result = orderCommentMapper.insert(comment);
            logger.info("插入结果: {}, 新记录id={}", result > 0 ? "成功" : "失败", comment.getId());
            return result > 0;
        }
    }
}