package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.data.source.NumberTypeSource;
import cc.xfl12345.mybigdata.server.common.data.source.StringTypeSource;
import cc.xfl12345.mybigdata.server.mysql.data.source.NumberTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.StringTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.NumberContentMapper;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.StringContentMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({MapperConfig.class})
public class AppDataSourceConfig {
    @Bean
    @ConditionalOnMissingBean
    public StringTypeSource stringTypeSource(StringContentMapper mapper) {
        StringTypeSourceImpl source = new StringTypeSourceImpl();
        source.setMapper(mapper);

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberTypeSource numberTypeSource(NumberContentMapper mapper) {
        NumberTypeSourceImpl source = new NumberTypeSourceImpl();
        source.setMapper(mapper);

        return source;
    }
}
