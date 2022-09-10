package cc.xfl12345.mybigdata.server.mysql.spring.boot.conf;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teasoft.honey.osql.core.BeeFactory;
import org.teasoft.honey.osql.core.SessionFactory;
import org.teasoft.spring.boot.config.BeeManageConfig;
import org.teasoft.spring.boot.config.BeeXmlConfiguration;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter({ BeeManageConfig.class, DataSourceAutoConfiguration.class})
@AutoConfigureBefore({BeeXmlConfiguration.class})
public class BeeFixConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(DataSource.class)
    public BeeFactory beeFactory(DataSource dataSource) {
        BeeFactory beeFactory = BeeFactory.getInstance();
        beeFactory.setDataSource(dataSource);
        return beeFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(BeeFactory.class)
    public SessionFactory sessionFactory(BeeFactory beeFactory) {
        SessionFactory factory = new SessionFactory();
        factory.setBeeFactory(beeFactory);
        return factory;
    }
}
