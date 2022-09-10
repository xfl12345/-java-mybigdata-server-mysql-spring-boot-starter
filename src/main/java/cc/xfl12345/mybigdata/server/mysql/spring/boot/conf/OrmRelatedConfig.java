package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.impl.CoreTableCache;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.teasoft.spring.boot.config.BeeAutoConfiguration;

@Configuration
@AutoConfigureAfter({BeeAutoConfiguration.class})
public class OrmRelatedConfig {
    // @DependsOn("sessionFactory")
    @Bean
    @ConditionalOnMissingBean
    public CoreTableCache coreTableCache() {
        return new CoreTableCache();
    }
}
