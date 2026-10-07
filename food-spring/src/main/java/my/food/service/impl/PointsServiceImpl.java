package my.food.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import my.food.entity.Food;
import my.food.entity.Points;
import my.food.entity.PointsExchange;
import my.food.entity.PointsRecord;
import my.food.mapper.FoodMapper;
import my.food.mapper.PointsExchangeMapper;
import my.food.mapper.PointsMapper;
import my.food.mapper.PointsRecordMapper;
import my.food.service.IPointsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointsServiceImpl implements IPointsService {

    private final PointsMapper pointsMapper;
    private final PointsRecordMapper pointsRecordMapper;
    private final PointsExchangeMapper pointsExchangeMapper;
    private final FoodMapper foodMapper;

    public PointsServiceImpl(PointsMapper pointsMapper, PointsRecordMapper pointsRecordMapper, 
                            PointsExchangeMapper pointsExchangeMapper, FoodMapper foodMapper) {
        this.pointsMapper = pointsMapper;
        this.pointsRecordMapper = pointsRecordMapper;
        this.pointsExchangeMapper = pointsExchangeMapper;
        this.foodMapper = foodMapper;
    }

    @Override
    public Points getPointsByUserId(Integer userId) {
        QueryWrapper<Points> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Points points = pointsMapper.selectOne(wrapper);
        if (points == null) {
            initUserPoints(userId);
            QueryWrapper<Points> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("user_id", userId);
            points = pointsMapper.selectOne(wrapper2);
        }
        return points;
    }

    @Override
    public void initUserPoints(Integer userId) {
        Points points = new Points(userId, 0, 0);
        pointsMapper.insert(points);
    }

    @Override
    @Transactional
    public void addPoints(Integer userId, Integer points, String reason) {
        pointsMapper.addPoints(userId, points);
        
        PointsRecord record = new PointsRecord();
        record.setUserId(userId);
        record.setChangeType(1);
        record.setPoints(points);
        record.setReason(reason);
        record.setCreatedAt(LocalDateTime.now());
        pointsRecordMapper.insert(record);
    }

    @Override
    @Transactional
    public boolean deductPoints(Integer userId, Integer points, String reason) {
        int affected = pointsMapper.deductPoints(userId, points);
        if (affected > 0) {
            PointsRecord record = new PointsRecord();
            record.setUserId(userId);
            record.setChangeType(2);
            record.setPoints(points);
            record.setReason(reason);
            record.setCreatedAt(LocalDateTime.now());
            pointsRecordMapper.insert(record);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean exchangeFood(Integer userId, Integer foodId) {
        Food food = foodMapper.selectById(foodId);
        if (food == null || food.getStatus() != 1) {
            return false;
        }

        int pointsNeeded = (int) Math.floor(food.getPrice());
        Points points = getPointsByUserId(userId);
        if (points.getAvailablePoints() < pointsNeeded) {
            return false;
        }

        boolean deductSuccess = deductPoints(userId, pointsNeeded, "积分兑换菜品: " + food.getName());
        if (!deductSuccess) {
            return false;
        }

        PointsExchange exchange = new PointsExchange();
        exchange.setUserId(userId);
        exchange.setFoodId(foodId);
        exchange.setPoints(pointsNeeded);
        exchange.setCreatedAt(LocalDateTime.now());
        pointsExchangeMapper.insert(exchange);
        
        return true;
    }

    @Override
    public List<PointsExchange> getExchangeHistory(Integer userId) {
        return pointsExchangeMapper.selectByUserId(userId);
    }

    @Override
    public List<PointsRecord> getPointsHistory(Integer userId) {
        return pointsRecordMapper.selectByUserIdOrderByCreatedAtDesc(userId);
    }
}