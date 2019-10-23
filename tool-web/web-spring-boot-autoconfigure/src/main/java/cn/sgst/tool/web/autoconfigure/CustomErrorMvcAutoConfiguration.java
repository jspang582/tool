package cn.sgst.tool.web.autoconfigure;

import cn.sgst.tool.web.core.error.CustomErrorAttributes;
import cn.sgst.tool.web.core.error.CustomErrorController;
import cn.sgst.tool.web.core.error.CustomExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * 替换Spring Boot默认的MVC error配置
 * 如果不使用这里自定义的配置,可在启动类中排除该配置
 * {@link EnableAutoConfiguration Auto-configuration} to render errors via an MVC error
 * controller.
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/17 11:17
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
public class CustomErrorMvcAutoConfiguration {

    @Bean
    public CustomErrorAttributes customErrorAttributes(){
        return new CustomErrorAttributes();
    }

    @Bean
    public CustomErrorController customErrorController(ErrorAttributes errorAttributes,ErrorViewResolver errorViewResolver){
        return new CustomErrorController(errorAttributes,errorViewResolver);
    }

    @Configuration
    static class ExceptionHandlerConfiguration{

        @Bean
        @ConditionalOnBean(DispatcherServlet.class)
        @ConditionalOnMissingBean
        public CustomExceptionHandler exceptionHandler (){
            return new CustomExceptionHandler();
        }
    }

}
