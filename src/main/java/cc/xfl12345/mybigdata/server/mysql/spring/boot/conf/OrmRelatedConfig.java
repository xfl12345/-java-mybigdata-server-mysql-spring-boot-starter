package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teasoft.honey.osql.core.SessionFactory;

@Configuration
public class OrmRelatedConfig {
    @Bean
    @ConditionalOnMissingBean
    public CoreTableCache coreTableCache(SessionFactory sessionFactory, ObjectMapper objectMapper) {
        CoreTableCache coreTableCache = new CoreTableCache();
        coreTableCache.setJacksonObjectMapper(objectMapper);
        return coreTableCache;
    }
}
