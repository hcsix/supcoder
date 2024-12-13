package com.supcoder.system

import com.supcoder.common.security.annotation.EnableCustomConfig
import com.supcoder.common.security.annotation.EnableRyFeignClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  ")
}
