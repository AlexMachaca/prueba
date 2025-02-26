package learning.DSWII.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import learning.DSWII.code.interceptor.SecurityInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("user2/public/**");
    }
}
