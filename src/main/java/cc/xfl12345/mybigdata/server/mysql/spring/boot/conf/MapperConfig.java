package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.TableMapperProperties;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoPack;
import com.fasterxml.uuid.NoArgGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    @ConditionalOnMissingBean
    public TableMapperProperties tableMapperProperties(AppIdTypeConverter idTypeConverter, CoreTableCache coreTableCache, NoArgGenerator uuidGenerator) {
        TableMapperProperties tableMapperProperties = new TableMapperProperties();
        tableMapperProperties.setIdTypeConverter(idTypeConverter);
        tableMapperProperties.setCoreTableCache(coreTableCache);
        tableMapperProperties.setUuidGenerator(uuidGenerator);

        return tableMapperProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DaoPack daoPack(TableMapperProperties tableMapperProperties) {
        DaoPack daoPack = new DaoPack();
        daoPack.setTableMapperProperties(tableMapperProperties);

        return daoPack;
    }
}
