package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.spring.web.controller.DruidStatController;
import cc.xfl12345.mybigdata.server.mysql.spring.web.interceptor.DruidStatInterceptor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DruidSpringMvcConfig implements WebMvcConfigurer {
    @Getter
    protected DruidStatInterceptor druidStatInterceptor;

    @Autowired
    public void setDruidStatInterceptor(DruidStatInterceptor druidStatInterceptor) {
        this.druidStatInterceptor = druidStatInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DruidStatController druidStatController() {
        return new DruidStatController();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Druid 的路由拦截器
        registry.addInterceptor(druidStatInterceptor).addPathPatterns(
            String.format("/%s/**", DruidStatController.servletName)
        );
    }
}
