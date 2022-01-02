package adls.demo_caldav

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(scanBasePackages = ["adls.demo_caldav.*"])
@ConfigurationPropertiesScan(basePackages = ["adls.demo_caldav.*"])
@EnableJpaRepositories
@EnableTransactionManagement
class DemoCaldavApplication

fun main(args: Array<String>) {
	runApplication<DemoCaldavApplication>(*args)
}
