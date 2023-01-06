package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.data.source.*;
import cc.xfl12345.mybigdata.server.mysql.data.source.DataSourceHomeImpl;
import cc.xfl12345.mybigdata.server.mysql.data.source.impl.*;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.MapperProperties;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoPack;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.bee.GlobalDataRecordBeeTableMapper;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.*;
import com.networknt.schema.JsonSchemaFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDataSourceConfig {
    @Bean
    @ConditionalOnMissingBean
    public IdDataSource idDataSource(DaoPack daoPack, GlobalDataRecordBeeTableMapper globalDataRecordMapper) {
        IdDataSourceImpl source = new IdDataSourceImpl();
        source.setGlobalDataRecordMapper(globalDataRecordMapper);
        source.setCoreTableCache(daoPack.getMapperProperties().getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringTypeSource stringTypeSource(DaoPack daoPack, IdDataSource idDataSource) {
        StringTypeSourceImpl source = new StringTypeSourceImpl();
        source.setIdDataSource(idDataSource);
        source.setMapper(daoPack.getBeeTableMapper(StringContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());
        source.setCoreTableCache(mapperProperties.getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberTypeSource numberTypeSource(DaoPack daoPack, IdDataSource idDataSource) {
        NumberTypeSourceImpl source = new NumberTypeSourceImpl();
        source.setIdDataSource(idDataSource);
        source.setMapper(daoPack.getBeeTableMapper(NumberContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());
        source.setCoreTableCache(mapperProperties.getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupTypeSource groupTypeSource(DaoPack daoPack,
                                           StringTypeSource stringTypeSource,
                                           IdDataSource idDataSource) {
        GroupTypeSourceImpl source = new GroupTypeSourceImpl();
        source.setIdDataSource(idDataSource);
        source.setStringTypeSource(stringTypeSource);

        source.setFirstMapper(daoPack.getBeeTableMapper(GroupRecord.class));
        source.setSecondMapper(daoPack.getBeeTableMapper(GroupContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());
        source.setCoreTableCache(mapperProperties.getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public JsonSchemaSource jsonSchemaSource(DaoPack daoPack,
                                             StringTypeSource stringTypeSource,
                                             IdDataSource idDataSource,
                                             JsonSchemaFactory jsonSchemaFactory) {
        JsonSchemaSourceImpl source = new JsonSchemaSourceImpl();
        source.setIdDataSource(idDataSource);
        source.setStringTypeSource(stringTypeSource);
        source.setJsonSchemaFactory(jsonSchemaFactory);
        source.setMapper(daoPack.getBeeTableMapper(TableSchemaRecord.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());
        source.setCoreTableCache(mapperProperties.getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectTypeSource objectTypeSource(DaoPack daoPack,
                                             StringTypeSource stringTypeSource,
                                             IdDataSource idDataSource) {
        ObjectTypeSourceImpl source = new ObjectTypeSourceImpl();
        source.setIdDataSource(idDataSource);
        source.setStringTypeSource(stringTypeSource);

        source.setFirstMapper(daoPack.getBeeTableMapper(ObjectRecord.class));
        source.setSecondMapper(daoPack.getBeeTableMapper(ObjectContent.class));

        MapperProperties mapperProperties = daoPack.getMapperProperties();
        source.setSqlErrorAnalyst(mapperProperties.getSqlErrorAnalyst());
        source.setCoreTableCache(mapperProperties.getCoreTableCache());

        return source;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceBag dataSourceBag(
        IdDataSource idDataSource,
        StringTypeSource stringTypeSource,
        NumberTypeSource numberTypeSource,
        GroupTypeSource groupTypeSource,
        JsonSchemaSource jsonSchemaSource,
        ObjectTypeSource objectTypeSource
        ) {

        DataSourceBag dataSourceBag = new DataSourceBag();
        dataSourceBag.setIdDataSource(idDataSource);
        dataSourceBag.setStringTypeSource(stringTypeSource);
        dataSourceBag.setNumberTypeSource(numberTypeSource);
        dataSourceBag.setGroupTypeSource(groupTypeSource);
        dataSourceBag.setJsonSchemaSource(jsonSchemaSource);
        dataSourceBag.setObjectTypeSource(objectTypeSource);

        return dataSourceBag;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceHome dataSourceHome(DataSourceBag dataSourceBag, CoreTableCache coreTableCache) {
        DataSourceHomeImpl dataSourceHome = new DataSourceHomeImpl();
        dataSourceHome.setDataSourceBag(dataSourceBag);
        dataSourceHome.setCoreTableCache(coreTableCache);

        return dataSourceHome;
    }
}
