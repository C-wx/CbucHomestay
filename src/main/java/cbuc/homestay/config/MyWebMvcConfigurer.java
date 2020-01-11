package cbuc.homestay.config;

import cbuc.homestay.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @Explain: web配置类
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/6
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    /**
     * @Explain 添加虚拟路径的映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**"). addResourceLocations("classpath:/static/");
        String uploadPath = "file:D://Cbuc_Homestay/upload/";
        registry.addResourceHandler("/upfiles/**").addResourceLocations(uploadPath);
    }

    /**
     * @Explain 添加拦截器链
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/toLogin", "/image/code", "/doLogin", "/getMsgCode", "/doForget")
                .excludePathPatterns("/static/**", "/js/**", "/css/**", "/img/**", "/plugins/**", "/vendor/**", "/error/**", "/image/**", "/upfiles/**");
    }
}
