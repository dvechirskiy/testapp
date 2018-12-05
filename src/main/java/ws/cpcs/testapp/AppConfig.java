package ws.cpcs.testapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.atomic.AtomicInteger;


@Configuration
@ComponentScan("ws.cpcs.testapp.domain")
@ComponentScan("ws.cpcs.testapp.services")
@EnableJpaRepositories( basePackages = "ws.cpcs.testapp.repository")
@EnableAsync
@Slf4j
public class AppConfig {
    public static final Integer MAX_COUNTER_NUMBER = 100;
    public static final Integer MIN_COUNTER_NUMBER = 0;

    public static final AtomicInteger COUNTER = new AtomicInteger(50);

    @Bean
    AsyncListenableTaskExecutor taskExecutor () {
        SimpleAsyncTaskExecutor t = new SimpleAsyncTaskExecutor();
        t.setConcurrencyLimit(100);
        return t;
    }

    @Bean
    ListenableFutureCallback<String> taskCallback () {
        return new TaskListenableFutureCallback();
    }

}
