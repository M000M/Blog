package cn.edu.pku.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   //这样才是一个有效的配置类
public class WebConfig implements WebMvcConfigurer {
    //必修是实现WebMvcConfigurer接口，原先的WebMvcConfigurerAdapter已经放弃，会出现拦截静态资源的问题

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())   //拦截器
                .addPathPatterns("/admin/**")  //前面带有admin的路径都被拦截了
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
