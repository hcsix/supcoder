package com.supcoder.hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@SpringBootApplication(SpringBootApplication.scanBasePackages = {"com.supcoder.hub"})
//@MapperScan("com.supcoder.hub.db.dao")
//@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
