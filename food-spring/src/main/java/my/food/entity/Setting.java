package my.food.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String name;

    private String value;


}
