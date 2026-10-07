package my.food.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("order1")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Double price;

    private Double promotion;

    private Integer number;

    private Integer isPay;

    private Integer isTaken;

    private String comment;

    private LocalDateTime createTime;

    private LocalDateTime payTime;

    private LocalDateTime takenTime;

    public Order() {
        price=0.0;
        promotion=0.0;
        number=0;
        isPay=0;
        isTaken=0;
        comment="";
    }
}
