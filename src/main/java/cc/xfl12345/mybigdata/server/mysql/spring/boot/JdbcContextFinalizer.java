package cc.xfl12345.mybigdata.server.mysql.spring.boot;

import cc.xfl12345.mybigdata.server.mysql.helper.DriverHelper;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * 这是一个用来执行正常退出任务的类
 */
@Slf4j
public class JdbcContextFinalizer implements ApplicationListener<ContextClosedEvent>, ApplicationContextAware, SignalHandler {
    protected ApplicationContext applicationContext;

    public JdbcContextFinalizer() {
        // 注册要监听的信号
        Signal.handle(new Signal("INT"), this);     // 2  : 中断（同 ctrl + c ）
        Signal.handle(new Signal("TERM"), this);    // 15 : 正常终止
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void handle(Signal signal) {
        String signalName = signal.getName();
        log.info(signalName+":"+signal.getNumber());
        if(signalName.equals("INT") || signalName.equals("TERM")) {
            SpringApplication.exit(applicationContext);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        AbandonedConnectionCleanupThread.checkedShutdown();
        ApplicationContext applicationContext = event.getApplicationContext();
        if (applicationContext.getParent() == null) {
            DriverHelper.deregister(applicationContext);
        }
    }
}
