package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 读取国际化配置信息工具类
 *
 * @author liweibing
 * @since 2018年7月4日
 */
@Component
public class MessageSourceUtil {

    @Autowired
    private MessageSource messageSource;

    /**
     * 根据code读取默认语言的配置信息
     *
     * @param code
     * @return String
     */
    public String getMessage(String code) {
        return this.getMessage(code, new Object[]{});
    }

    /**
     * 根据code读取默认语言的配置信息
     *
     * @param code
     * @param defaultMessage
     * @return String
     */
    public String getMessage(String code, String defaultMessage) {
        return this.getMessage(code, null, defaultMessage);
    }


    /**
     * 根据code读取默认语言的配置信息
     *
     * @param code
     * @param defaultMessage
     * @param locale
     * @return String
     */
    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.getMessage(code, null, defaultMessage, locale);
    }

    /**
     * 根据code读取默认语言的配置信息
     *
     * @param code   not null
     * @param locale not null
     * @return String
     */
    public String getMessage(String code, Locale locale) {
        return this.getMessage(code, null, "", locale);
    }

    /**
     * 根据code读取默认语言的配置信息
     *
     * @param code not null
     * @param args not null
     * @return String
     */
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");

    }

    /**
     * 根据code，locale读取相应的配置信息
     *
     * @param code   not null
     * @param args   not null
     * @param locale not null
     * @return String
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, "", locale);
    }

    /**
     * 根据code读取默认语言的配置信息，可设置默认值
     *
     * @param code           not null
     * @param args           not null
     * @param defaultMessage not null
     * @return String
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = Locale.CHINESE;
        return this.getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 根据code，locale读取相应的配置信息，可设置默认值
     *
     * @param code           not null
     * @param args           not null
     * @param defaultMessage not null
     * @param locale         not null
     * @return String
     */
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
