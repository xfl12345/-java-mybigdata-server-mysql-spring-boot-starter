package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.web.controller.DruidStatController;
import cc.xfl12345.mybigdata.server.mysql.web.interceptor.DruidStatInterceptor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

@Configuration
public class DruidSpringMvcConfig implements WebMvcConfigurer {
    @Getter
    protected DruidStatInterceptor druidStatInterceptor;

    @Autowired
    public void setDruidStatInterceptor(DruidStatInterceptor druidStatInterceptor) {
        this.druidStatInterceptor = druidStatInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        registry.addResourceHandler(
            String.format("/%s/**", DruidStatController.servletName)
        ).addResourceLocations(
            new UrlResource(Objects.requireNonNull(classLoader.getResource("support/http/resources/")))
        );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Druid 的路由拦截器
        registry.addInterceptor(druidStatInterceptor).addPathPatterns(
            String.format("/%s/**", DruidStatController.servletName)
        );
    }
}
