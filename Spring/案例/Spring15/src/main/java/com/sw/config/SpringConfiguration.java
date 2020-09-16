package com.sw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring的配置类，相当于beans。xml
 */
@Configuration
@ComponentScan("com.sw")
@Import({JdbcConfig.class,TransactionConfig.class})
@PropertySource("jdbc.properties")
@EnableTransactionManagement
public class SpringConfiguration {
}
