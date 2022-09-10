package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.helper.MyDatabaseInitializer;
import cc.xfl12345.mybigdata.server.mysql.spring.boot.JdbcContextFinalizer;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.teasoft.spring.boot.config.BeeProperties;

import javax.sql.DataSource;

@Configuration
@AutoConfigureBefore({DruidDataSourceAutoConfigure.class, BeeProperties.class})
public class DatabaseDataSourceConfig {
    @Bean
    @Scope("prototype")
    public MyDatabaseInitializer myDatabaseInitializer() {
        return new MyDatabaseInitializer();
    }

    @DependsOn("myDatabaseInitializer")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean("dataSource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConditionalOnMissingBean
    public JdbcContextFinalizer jdbcContextFinalizer(ApplicationContext applicationContext) {
        JdbcContextFinalizer finalizer = new JdbcContextFinalizer();
        finalizer.setApplicationContext(applicationContext);
        return finalizer;
    }
}
