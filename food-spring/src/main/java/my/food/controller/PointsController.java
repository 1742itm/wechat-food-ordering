package my.food.controller;

import my.food.entity.Food;
import my.food.entity.Points;
import my.food.entity.PointsExchange;
import my.food.entity.PointsRecord;
import my.food.entity.User;
import my.food.mapper.FoodMapper;
import my.food.service.IPointsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/points")
public class PointsController {

    private final IPointsService pointsService;
    private final FoodMapper foodMapper;
    
    @Value("${host}")
    private String host;

    public PointsController(IPointsService pointsService, FoodMapper foodMapper) {
        this.pointsService = pointsService;
        this.foodMapper = foodMapper;
    }

    @GetMapping("/get")
    public Map<String, Object> getPoints(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        Points points = pointsService.getPointsByUserId(user.getId());
        result.put("success", true);
        result.put("points", points.getAvailablePoints());
        return result;
    }

    @GetMapping("/goods")
    public Map<String, Object> getGoods() {
        Map<String, Object> result = new HashMap<>();
        List<Food> foods = foodMapper.selectList(null);
        List<Map<String, Object>> list = foods.stream()
                .filter(food -> food.getStatus() == 1)
                .map(food -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", food.getId());
                    item.put("name", food.getName());
                    item.put("desc", "");
                    item.put("image", host + "/" + food.getImageUrl());
                    item.put("points", (int) Math.floor(food.getPrice()));
                    item.put("stock", 999);
                    return item;
                }).collect(Collectors.toList());
        result.put("success", true);
        result.put("list", list);
        return result;
    }

    @GetMapping("/history")
    public Map<String, Object> getHistory(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        List<PointsRecord> records = pointsService.getPointsHistory(user.getId());
        List<Map<String, Object>> list = records.stream().map(record -> {
            Map<String, Object> item = new HashMap<>();
            item.put("id", record.getId());
            item.put("type", record.getChangeType() == 1 ? "add" : "reduce");
            item.put("points", record.getPoints());
            item.put("desc", record.getReason());
            item.put("time", record.getCreatedAt().toString());
            return item;
        }).collect(Collectors.toList());
        result.put("success", true);
        result.put("list", list);
        return result;
    }

    @GetMapping("/exchangeHistory")
    public Map<String, Object> getExchangeHistory(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        List<PointsExchange> exchanges = pointsService.getExchangeHistory(user.getId());
        List<Map<String, Object>> list = exchanges.stream().map(exchange -> {
            Food food = foodMapper.selectById(exchange.getFoodId());
            Map<String, Object> item = new HashMap<>();
            item.put("id", exchange.getId());
            item.put("foodId", exchange.getFoodId());
            item.put("foodName", food != null ? food.getName() : "未知菜品");
            item.put("points", exchange.getPoints());
            item.put("time", exchange.getCreatedAt().toString());
            return item;
        }).collect(Collectors.toList());
        result.put("success", true);
        result.put("list", list);
        return result;
    }

    @PostMapping("/exchange")
    public Map<String, Object> exchange(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        Integer foodId = (Integer) params.get("foodId");
        if (foodId == null) {
            result.put("success", false);
            result.put("message", "请选择要兑换的菜品");
            return result;
        }
        Food food = foodMapper.selectById(foodId);
        if (food == null || food.getStatus() != 1) {
            result.put("success", false);
            result.put("message", "菜品不存在或已下架");
            return result;
        }
        int pointsNeeded = (int) Math.floor(food.getPrice());
        Points points = pointsService.getPointsByUserId(user.getId());
        if (points.getAvailablePoints() < pointsNeeded) {
            result.put("success", false);
            result.put("message", "积分不足，需要" + pointsNeeded + "积分");
            return result;
        }
        boolean success = pointsService.exchangeFood(user.getId(), foodId);
        if (success) {
            result.put("success", true);
            result.put("message", "兑换成功");
        } else {
            result.put("success", false);
            result.put("message", "兑换失败");
        }
        return result;
    }
}