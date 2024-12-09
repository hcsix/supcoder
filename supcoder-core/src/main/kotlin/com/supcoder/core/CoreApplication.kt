package com.supcoder.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan("com.supcoder.core.model")
@EnableJpaRepositories("com.supcoder.core.repository")
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
