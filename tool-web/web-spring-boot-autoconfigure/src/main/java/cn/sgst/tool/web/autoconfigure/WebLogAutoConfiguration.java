package cn.sgst.tool.web.autoconfigure;

import cn.sgst.tool.web.core.aspect.WebLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * web日志配置
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/16 14:25
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebLogAutoConfiguration {

    @Bean
    public WebLogAspect webLogAspect(){
        return new WebLogAspect();
    }
}
