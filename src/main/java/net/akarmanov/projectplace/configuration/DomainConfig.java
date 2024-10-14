package net.akarmanov.projectplace.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "net.akarmanov.projectplace.domain")
@EnableJpaRepositories(basePackages = "net.akarmanov.projectplace.repos")
@EnableTransactionManagement
public class DomainConfig {
}
