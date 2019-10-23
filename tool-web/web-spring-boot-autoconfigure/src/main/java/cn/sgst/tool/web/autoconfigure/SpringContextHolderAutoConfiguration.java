package cn.sgst.tool.web.autoconfigure;

import cn.sgst.tool.web.core.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring ApplicationContext自动配置
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/16 15:17
 */
@Configuration
public class SpringContextHolderAutoConfiguration {

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
}
