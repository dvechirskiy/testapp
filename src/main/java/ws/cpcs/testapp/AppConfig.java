package ws.cpcs.testapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
    TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(100);
        executor.setCorePoolSize(100);
        executor.setQueueCapacity(200);
        return executor;
    }

}
