package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import cc.xfl12345.mybigdata.server.common.web.mapper.AccountMapper;
import cc.xfl12345.mybigdata.server.mysql.database.converter.AppIdTypeConverter;
import cc.xfl12345.mybigdata.server.mysql.database.mapper.AuthAccountMapper;
import cc.xfl12345.mybigdata.server.mysql.mapper.AccountMapperImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({MapperConfig.class})
public class AccountMapper4WebConfig {
    @Bean
    @ConditionalOnMissingBean
    public AccountMapper accountMapper(AuthAccountMapper mapper, AppIdTypeConverter idTypeConverter) {
        AccountMapperImpl accountMapper = new AccountMapperImpl();
        accountMapper.setAuthAccountMapper(mapper);
        accountMapper.setIdTypeConverter(idTypeConverter);

        return accountMapper;
    }
}
