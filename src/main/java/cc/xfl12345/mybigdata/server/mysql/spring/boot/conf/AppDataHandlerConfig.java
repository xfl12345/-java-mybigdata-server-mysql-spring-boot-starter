package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.data.handler.GlobalDataRecordHandler;
import cc.xfl12345.mybigdata.server.common.data.handler.NumberTypeHandler;
import cc.xfl12345.mybigdata.server.common.data.handler.StringTypeHandler;
import cc.xfl12345.mybigdata.server.common.data.source.IdDataSource;
import cc.xfl12345.mybigdata.server.common.data.source.NumberTypeSource;
import cc.xfl12345.mybigdata.server.common.data.source.StringTypeSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDataHandlerConfig {
    @Bean
    @ConditionalOnMissingBean
    public StringTypeHandler stringTypeHandler(StringTypeSource source) {
        StringTypeHandler handler = new StringTypeHandler();
        handler.setDataSource(source);

        return handler;
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberTypeHandler numberTypeHandler(NumberTypeSource source) {
        NumberTypeHandler handler = new NumberTypeHandler();
        handler.setDataSource(source);

        return handler;
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalDataRecordHandler globalDataRecordHandler(IdDataSource source) {
        GlobalDataRecordHandler handler = new GlobalDataRecordHandler();
        handler.setDataSource(source);

        return handler;
    }
}
