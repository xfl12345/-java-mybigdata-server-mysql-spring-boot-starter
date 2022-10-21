package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.data.source.IdDataSource;
import cc.xfl12345.mybigdata.server.common.data.source.NumberTypeSource;
import cc.xfl12345.mybigdata.server.common.data.source.StringTypeSource;
import cc.xfl12345.mybigdata.server.mysql.data.source.IdDataSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.NumberTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.StringTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoPack;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.GlobalDataRecord;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.NumberContent;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.StringContent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDataSourceConfig {
    @Bean
    @ConditionalOnMissingBean
    public StringTypeSource stringTypeSource(DaoPack daoPack) {
        StringTypeSourceImpl source = new StringTypeSourceImpl();
        source.setMapper(daoPack.getMapper(StringContent.class));

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberTypeSource numberTypeSource(DaoPack daoPack) {
        NumberTypeSourceImpl source = new NumberTypeSourceImpl();
        source.setMapper(daoPack.getMapper(NumberContent.class));

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public IdDataSource idDataSource(DaoPack daoPack, AppIdTypeConverter idTypeConverter) {
        IdDataSourceImpl source = new IdDataSourceImpl();
        source.setMapper(daoPack.getMapper(GlobalDataRecord.class));
        source.setIdTypeConverter(idTypeConverter);

        return source;
    }
}
