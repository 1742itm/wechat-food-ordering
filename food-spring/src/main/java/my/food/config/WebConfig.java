package my.food.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${defaultImagesDir}")
    private String uploadImagesDir;//注入application.yml中的uploadImagesDir
    @Value("${foodImagesDir}")
    private String foodImagesDir;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置上传虚拟路径
        //将浏览器请求的以/images开头的网址，理解为请求E:\img\images\文件夹下的文件
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + uploadImagesDir);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + foodImagesDir);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
