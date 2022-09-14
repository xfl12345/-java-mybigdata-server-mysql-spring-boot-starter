package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.database.error.SqlErrorAnalyst;
import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.error.SqlErrorAnalystImpl;
import cc.xfl12345.mybigdata.server.mysql.web.controller.DruidStatController;
import cc.xfl12345.mybigdata.server.mysql.web.interceptor.DruidStatInterceptor;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DruidStatController.class)
public class NormalConfig {
    @Bean
    @ConditionalOnMissingBean
    public SqlErrorAnalyst sqlErrorAnalyst() {
        return new SqlErrorAnalystImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public NoArgGenerator uuidGenerator() {
        return Generators.timeBasedGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public AppIdTypeConverter idTypeConverter() {
        return new AppIdTypeConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }
}
