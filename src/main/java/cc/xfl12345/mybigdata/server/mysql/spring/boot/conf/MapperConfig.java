package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.*;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.impl.AbstractAppTableMapper;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.impl.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.GlobalDataRecordMapperImpl;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.orm.bee.config.BeeTableMapperConfig;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.orm.bee.config.BeeTableMapperConfigGenerator;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.*;
import com.fasterxml.uuid.NoArgGenerator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({OrmRelatedConfig.class})
public class MapperConfig {
    @Getter
    protected ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <TablePojoType> BeeTableMapperConfig<TablePojoType> getHandlerConfig(Class<TablePojoType> cls)
        throws NoSuchMethodException {
        return BeeTableMapperConfigGenerator.getConfig(cls);
    }

    @SuppressWarnings("unchecked")
    public <T> AbstractAppTableMapper<T> getMapper(Class<T> cls) throws Exception {
        Class<AbstractAppTableMapper<T>> mapperClass = (Class<AbstractAppTableMapper<T>>) Class.forName(
            GlobalDataRecordMapperImpl.class.getPackageName() + "." + cls.getSimpleName() + "MapperImpl"
        );
        AbstractAppTableMapper<T> mapper = mapperClass.getDeclaredConstructor().newInstance();
        mapper.setIdTypeConverter(applicationContext.getBean("idTypeConverter", AppIdTypeConverter.class));
        mapper.setCoreTableCache(applicationContext.getBean("coreTableCache", CoreTableCache.class));
        mapper.setUuidGenerator(applicationContext.getBean("uuidGenerator", NoArgGenerator.class));
        mapper.setMapperConfig(getHandlerConfig(cls));

        return mapper;
    }


    @Bean
    @ConditionalOnMissingBean
    public AuthAccountMapper authAccountMapper() throws Exception {
        return (AuthAccountMapper) getMapper(AuthAccount.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalDataRecordMapper globalDataRecordMapper() throws Exception {
        return (GlobalDataRecordMapper) getMapper(GlobalDataRecord.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupContentMapper groupContentMapper() throws Exception {
        return (GroupContentMapper) getMapper(GroupContent.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupRecordMapper groupRecordMapper() throws Exception {
        return (GroupRecordMapper) getMapper(GroupRecord.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberContentMapper numberContentMapper() throws Exception {
        return (NumberContentMapper) getMapper(NumberContent.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectContentMapper objectContentMapper() throws Exception {
        return (ObjectContentMapper) getMapper(ObjectContent.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectRecordMapper objectRecordMapper() throws Exception {
        return (ObjectRecordMapper) getMapper(ObjectRecord.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public StringContentMapper stringContentMapper() throws Exception {
        return (StringContentMapper) getMapper(StringContent.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public TableSchemaRecordMapper tableSchemaRecordMapper() throws Exception {
        return (TableSchemaRecordMapper) getMapper(TableSchemaRecord.class);
    }
}
