package com.supcoder.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务
 * 
 * @author supcoder
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SupcoderFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SupcoderFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
