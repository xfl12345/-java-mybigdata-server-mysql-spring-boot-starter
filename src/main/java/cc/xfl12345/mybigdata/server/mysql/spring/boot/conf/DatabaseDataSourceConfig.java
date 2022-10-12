package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.spring.helper.JdbcContextFinalizer;
import cc.xfl12345.mybigdata.server.mysql.spring.helper.MyDatabaseInitializer;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.teasoft.spring.boot.config.BeeProperties;

@Configuration
@AutoConfigureBefore({DruidDataSourceAutoConfigure.class, BeeProperties.class})
public class DatabaseDataSourceConfig {
    @Bean
    @Scope("prototype")
    public MyDatabaseInitializer myDatabaseInitializer() {
        return new MyDatabaseInitializer();
    }

    @DependsOn({"myDatabaseInitializer"})
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        return new DruidDataSourceWrapper();
    }

    @Bean
    @ConditionalOnMissingBean
    public JdbcContextFinalizer jdbcContextFinalizer() {
        return new JdbcContextFinalizer();
    }
}
