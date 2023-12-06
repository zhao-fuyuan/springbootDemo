package com.example.demo.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/v1/users/list");
        pathPatterns.add("/v1/user/update");
        pathPatterns.add("/v1/start");
        pathPatterns.add("/order/**");
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/jwt/token");
        registry.addInterceptor(jwtInterceptor) // 添加拦截器
                .addPathPatterns(pathPatterns) // 添加拦截url
                .excludePathPatterns(excludePathPatterns); // 添加不拦截url
    }
}