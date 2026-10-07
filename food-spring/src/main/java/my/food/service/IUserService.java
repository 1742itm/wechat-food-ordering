package my.food.service;

import jakarta.servlet.http.HttpServletRequest;
import my.food.bean.Param;
import my.food.bean.Result;
import my.food.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;



public interface IUserService extends IService<User> {
    Result login(String code, HttpServletRequest request);
}
