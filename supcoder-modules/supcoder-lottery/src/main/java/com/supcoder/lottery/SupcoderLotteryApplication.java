package com.supcoder.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.supcoder.common.security.annotation.EnableCustomConfig;
import com.supcoder.common.security.annotation.EnableRyFeignClients;


@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class SupcoderLotteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupcoderLotteryApplication.class, args);
    }

}
