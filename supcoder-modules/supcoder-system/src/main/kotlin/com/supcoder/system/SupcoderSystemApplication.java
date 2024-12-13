package com.supcoder.system;

import com.supcoder.common.security.annotation.EnableCustomConfig;
import com.supcoder.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class SupcoderSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SupcoderSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
