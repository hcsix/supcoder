package com.supcoder.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.supcoder.common.security.annotation.EnableCustomConfig;
import com.supcoder.common.security.annotation.EnableRyFeignClients;

/**
 * 定时任务
 * 
 * @author supcoder
 */
@EnableCustomConfig
@EnableRyFeignClients   
@SpringBootApplication
public class SupcoderJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SupcoderJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
