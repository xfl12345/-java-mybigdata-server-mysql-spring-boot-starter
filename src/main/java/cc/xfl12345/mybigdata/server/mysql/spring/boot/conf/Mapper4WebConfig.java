package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.web.mapper.AccountMapper;
import cc.xfl12345.mybigdata.server.common.web.mapper.AdvanceSearchMapper;
import cc.xfl12345.mybigdata.server.common.web.mapper.DatabaseViewer;
import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.AuthAccountMapper;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.impl.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.mapper.AccountMapperImpl;
import cc.xfl12345.mybigdata.server.mysql.mapper.AdvanceSearchMapperImpl;
import cc.xfl12345.mybigdata.server.mysql.mapper.DatabaseViewerImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({MapperConfig.class})
public class Mapper4WebConfig {
    @Bean
    @ConditionalOnMissingBean
    public AccountMapper accountMapper(AuthAccountMapper mapper, AppIdTypeConverter idTypeConverter) {
        AccountMapperImpl accountMapper = new AccountMapperImpl();
        accountMapper.setAuthAccountMapper(mapper);
        accountMapper.setIdTypeConverter(idTypeConverter);

        return accountMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public AdvanceSearchMapper advanceSearchMapper(CoreTableCache coreTableCache) {
        return new AdvanceSearchMapperImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public DatabaseViewer databaseViewer(CoreTableCache coreTableCache) throws Exception {
        return new DatabaseViewerImpl();
    }
}
