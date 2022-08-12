package com.superCloud.config.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 设置认证界面中文
 * @author ys
 * @date 2022/8/3 13:30
 */
@Configuration
public class ReloadMessageConfig {

    @Bean // 加载中文的认证提示信息
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //.properties 不要加到后面
        messageSource.setBasename("classpath:messages_zh_CN");
        return messageSource;
    }
}
