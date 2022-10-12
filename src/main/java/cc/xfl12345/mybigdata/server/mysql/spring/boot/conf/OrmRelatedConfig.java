package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teasoft.honey.osql.core.SessionFactory;

@Configuration
public class OrmRelatedConfig {
    @Bean
    @ConditionalOnMissingBean
    public CoreTableCache coreTableCache(SessionFactory sessionFactory) {
        return new CoreTableCache();
    }
}
