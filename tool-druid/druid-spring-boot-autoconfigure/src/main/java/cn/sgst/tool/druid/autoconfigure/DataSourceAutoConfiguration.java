package cn.sgst.tool.druid.autoconfigure;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @title: MybatisDataSourceAutoConfiguration
 * @description: Druid数据库连接池配置
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:28
 */
@Configuration
@ConditionalOnProperty(prefix = "db", name = "url")
@EnableConfigurationProperties({DruidProperties.class})
public class DataSourceAutoConfiguration {

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

}
