package cn.sgst.tool.web.autoconfigure;

import cn.sgst.tool.common.xss.XssFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Xss自动配置
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/16 14:27
 */
@Configuration
@EnableConfigurationProperties({XssProperties.class})
public class XssFilterAutoConfiguration {


    /**
     * xssFilter注册
     */

    @Bean
    public FilterRegistrationBean xssFilterRegistration(XssProperties xssProperties) {
        XssFilter xssFilter = new XssFilter();
        // 这里可以加不被xss过滤的接口
        xssFilter.setUrlExclusion(xssProperties.getExclusionUrls());
        FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
