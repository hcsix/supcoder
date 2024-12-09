package com.supcoder.hub.dashboard

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(
    scanBasePackages = ["com.supcoder.hub.db",
        "com.supcoder.hub.core",
        "com.supcoder.hub.dashboard"]
)
@MapperScan("com.supcoder.hub.db.dao")
@EnableTransactionManagement
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
