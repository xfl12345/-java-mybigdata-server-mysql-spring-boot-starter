package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoManager;
import com.fasterxml.uuid.NoArgGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    @ConditionalOnMissingBean
    public DaoManager daoManager(AppIdTypeConverter idTypeConverter, CoreTableCache coreTableCache, NoArgGenerator uuidGenerator) {
        DaoManager daoManager = new DaoManager();
        daoManager.setIdTypeConverter(idTypeConverter);
        daoManager.setCoreTableCache(coreTableCache);
        daoManager.setUuidGenerator(uuidGenerator);

        return daoManager;
    }
}
