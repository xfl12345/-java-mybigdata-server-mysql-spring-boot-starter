package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.data.source.GlobalDataRecordDataSource;
import cc.xfl12345.mybigdata.server.common.data.source.GroupTypeSource;
import cc.xfl12345.mybigdata.server.common.data.source.NumberTypeSource;
import cc.xfl12345.mybigdata.server.common.data.source.StringTypeSource;
import cc.xfl12345.mybigdata.server.mysql.api.GlobalDataRecordDataSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.impl.GroupTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.impl.NumberTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.impl.StringTypeSourceImpl;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.MapperProperties;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoPack;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDataSourceConfig {
    @Bean
    @ConditionalOnMissingBean(value = {GlobalDataRecordDataSource.class})
    public GlobalDataRecordDataSourceImpl globalDataRecordDataSource(DaoPack daoPack) {
        MapperProperties mapperProperties = daoPack.getMapperProperties();

        GlobalDataRecordDataSourceImpl source = new GlobalDataRecordDataSourceImpl();
        source.setCoreTableCache(mapperProperties.getCoreTableCache());
        source.setUuidGenerator(mapperProperties.getUuidGenerator());
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());

        source.setTableMapper(daoPack.getBeeTableMapper(GlobalDataRecord.class));

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringTypeSource stringTypeSource(DaoPack daoPack, GlobalDataRecordDataSource globalDataRecordDataSource) {
        StringTypeSourceImpl source = new StringTypeSourceImpl();
        source.setGlobalDataRecordDataSource(globalDataRecordDataSource);
        source.setMapper(daoPack.getBeeTableMapper(StringContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberTypeSource numberTypeSource(DaoPack daoPack, GlobalDataRecordDataSource globalDataRecordDataSource) {
        NumberTypeSourceImpl source = new NumberTypeSourceImpl();
        source.setGlobalDataRecordDataSource(globalDataRecordDataSource);
        source.setMapper(daoPack.getBeeTableMapper(NumberContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupTypeSource groupTypeSource(DaoPack daoPack,
                                           StringTypeSource stringTypeSource,
                                           GlobalDataRecordDataSource globalDataRecordDataSource) {
        GroupTypeSourceImpl source = new GroupTypeSourceImpl();
        source.setGlobalDataRecordDataSource(globalDataRecordDataSource);
        source.setStringTypeSource(stringTypeSource);

        source.setFirstMapper(daoPack.getBeeTableMapper(GroupRecord.class));
        source.setSecondMapper(daoPack.getBeeTableMapper(GroupContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());

        return source;
    }
}
