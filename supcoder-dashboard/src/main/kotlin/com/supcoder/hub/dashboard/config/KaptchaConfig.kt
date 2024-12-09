package com.supcoder.hub.dashboard.config

import com.google.code.kaptcha.Producer
import com.google.code.kaptcha.impl.DefaultKaptcha
import com.google.code.kaptcha.util.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Properties

/**
 * KaptchaConfig
 *
 * @author lee
 * @date 2024/12/9
 */
@Configuration
open class KaptchaConfig {

    @Bean
    open fun kaptchaProducer(): Producer {
        val properties = Properties()
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        val kaptcha = DefaultKaptcha();
        val config = Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}