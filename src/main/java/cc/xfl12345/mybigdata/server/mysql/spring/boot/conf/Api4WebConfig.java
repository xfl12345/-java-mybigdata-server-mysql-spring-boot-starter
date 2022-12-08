package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.api.AccountMapper;
import cc.xfl12345.mybigdata.server.common.api.AdvanceSearchMapper;
import cc.xfl12345.mybigdata.server.common.api.DatabaseViewer;
import cc.xfl12345.mybigdata.server.common.api.IdViewer;
import cc.xfl12345.mybigdata.server.mysql.api.AccountMapperImpl;
import cc.xfl12345.mybigdata.server.mysql.api.AdvanceSearchMapperImpl;
import cc.xfl12345.mybigdata.server.mysql.api.DatabaseViewerImpl;
import cc.xfl12345.mybigdata.server.mysql.api.IdViewerImpl;
import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.base.CoreTableCache;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.impl.DaoPack;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.AuthAccount;
import cc.xfl12345.mybigdata.server.mysql.database.pojo.GlobalDataRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Api4WebConfig {
    @Bean
    @ConditionalOnMissingBean
    public AccountMapper accountMapper(DaoPack daoPack, AppIdTypeConverter idTypeConverter) {
        AccountMapperImpl accountMapper = new AccountMapperImpl();
        accountMapper.setAuthAccountMapper(daoPack.getTableBasicMapper(AuthAccount.class));
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
    public DatabaseViewer databaseViewer(CoreTableCache coreTableCache) {
        DatabaseViewerImpl databaseViewer = new DatabaseViewerImpl();
        databaseViewer.setCoreTableCache(coreTableCache);

        return databaseViewer;
    }

    @Bean
    @ConditionalOnMissingBean
    public IdViewer idViewer(CoreTableCache coreTableCache, DaoPack daoPack) {
        IdViewerImpl idViewer = new IdViewerImpl();
        idViewer.setCoreTableCache(coreTableCache);
        idViewer.setGlobalDataRecordMapper(daoPack.getTableBasicMapper(GlobalDataRecord.class));

        return idViewer;
    }
}
