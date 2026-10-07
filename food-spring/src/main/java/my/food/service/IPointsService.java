package my.food.service;

import my.food.entity.Points;
import my.food.entity.PointsExchange;
import my.food.entity.PointsRecord;

import java.util.List;

public interface IPointsService {
    Points getPointsByUserId(Integer userId);
    
    void initUserPoints(Integer userId);
    
    void addPoints(Integer userId, Integer points, String reason);
    
    boolean deductPoints(Integer userId, Integer points, String reason);
    
    boolean exchangeFood(Integer userId, Integer foodId);
    
    List<PointsExchange> getExchangeHistory(Integer userId);
    
    List<PointsRecord> getPointsHistory(Integer userId);
}