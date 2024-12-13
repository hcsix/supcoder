package com.supcoder.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.supcoder.common.security.annotation.EnableCustomConfig;
import com.supcoder.common.security.annotation.EnableRyFeignClients;

/**
 * 代码生成
 * 
 * @author supcoder
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class SupcoderGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SupcoderGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
