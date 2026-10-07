package my.food.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.food.bean.Param;
import my.food.bean.Result;
import my.food.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    //重要重要重要重要重要重要重要重要重要重要重要重要重要重要重要
    //微信登录技术介绍：https://blog.csdn.net/gtLBTNq9mr3/article/details/124564257?share_token=2c736052-ca94-4f8c-aa4e-57d12f309ea1
    @GetMapping("/login")
    public Result login(Param param, HttpServletRequest request) {
        Result result=new Result();
        try{
            result=userService.login(param.getJs_code(),request);
        }catch (Exception e){
            result.setIsLogin(false);
            e.printStackTrace();
        }
        return  result;
    }

    @GetMapping("/checkLogin")
    public Result checkLogin(HttpServletRequest request){
        Result result=new Result();
        HttpSession session=request.getSession();

        //如果已登录，则session中存放有user
        if(session.getAttribute("user")==null)
            result.setIsLogin(false);//表示未登录状态
        else
            //表示曾经登录成功过，目前处于登录成功状态
            result.setIsLogin(true);

        return  result;
    }
}
