package my.food.bean;

import lombok.Data;

@Data
public class WxSession {
    private String session_key;
    private String openid;
    private String token;
    private Integer credit;
}
