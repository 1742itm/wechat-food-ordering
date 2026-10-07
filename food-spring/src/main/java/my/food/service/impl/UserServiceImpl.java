package my.food.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.food.bean.Result;
import my.food.entity.dto.SettingDto;
import my.food.bean.WxSession;
import my.food.entity.Setting;
import my.food.entity.User;
import my.food.mapper.SettingMapper;
import my.food.mapper.UserMapper;
import my.food.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.food.tool.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Value("${host}")
    private String host;//注入application中的host

    public Result login(String code, HttpServletRequest request){
        List<Setting> settings= settingMapper.selectAll();
        SettingDto settingDto=SettingDto.toSettingDto(settings,host);
        System.out.println(request.getCookies());
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + settingDto.getAppid() +
                "&secret=" + settingDto.getAppsecret() + "&js_code=" + code + "&grant_type=authorization_code";
        //通过java语言发起网络请求
        String resultString= HttpClientUtils.doPostJson(url,"");
        System.out.println(resultString);
        //通过谷歌的Gson库，可以在java对象和json字符串之间互相转换
        Gson gson=new Gson();
        WxSession wxSession=gson.fromJson(resultString, WxSession.class);

        Result result=new Result();
        result.setIsLogin(false);

        if(wxSession.getOpenid()!=null && !wxSession.getOpenid().isEmpty()){
            User user=userMapper.findByOpenid(wxSession.getOpenid());
            //
            if(user==null){
                user=new User();
                user.setOpenid(wxSession.getOpenid());
                userMapper.insert(user);
            }

            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            result.setIsLogin(true);
        }else{
            //表示向微信官方获取当前正要登录的微信用户的openid失败，即登陆失败。
            //略
        }
        return  result;
    }
}
