package my.food.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("points")
public class Points {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer totalPoints;
    private Integer availablePoints;

    public Points() {}

    public Points(Integer userId, Integer totalPoints, Integer availablePoints) {
        this.userId = userId;
        this.totalPoints = totalPoints;
        this.availablePoints = availablePoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(Integer availablePoints) {
        this.availablePoints = availablePoints;
    }
}