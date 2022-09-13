package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.impl.CoreTableCache;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teasoft.honey.osql.core.SessionFactory;
import org.teasoft.spring.boot.config.BeeAutoConfiguration;

@Configuration
@AutoConfigureAfter({BeeAutoConfiguration.class})
public class OrmRelatedConfig {
    @Bean
    @ConditionalOnMissingBean
    public CoreTableCache coreTableCache(SessionFactory sessionFactory) {
        return new CoreTableCache();
    }
}
