package cn.sgst.tool.druid.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid基本属性配置
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 10:16
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "db")
public class DruidProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Integer initialSize = 2;

    private Integer minIdle = 1;

    private Integer maxActive = 20;

    private Integer maxWait = 60000;

    private Integer timeBetweenEvictionRunsMillis = 60000;

    private Integer minEvictableIdleTimeMillis = 300000;

    private String validationQuery;

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String filters = "stat";

    private String connectionProperties;


    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);


        //configuration
        dataSource.setInitialSize(initialSize);     //定义初始连接数
        dataSource.setMinIdle(minIdle);             //最小空闲
        dataSource.setMaxActive(maxActive);         //定义最大连接数
        dataSource.setMaxWait(maxWait);             //最长等待时间

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(getValidateQueryByUrl(url));
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
        }
        dataSource.setConnectionProperties(connectionProperties);

    }

    public Properties createProperties() {
        Properties properties = new Properties();
        properties.put("url", this.url);
        properties.put("username", this.username);
        properties.put("password", this.password);
        properties.put("driverClassName", this.driverClassName);
        properties.put("initialSize", this.initialSize);
        properties.put("maxActive", this.maxActive);
        properties.put("minIdle", this.minIdle);
        properties.put("maxWait", this.maxWait);
        properties.put("poolPreparedStatements", this.poolPreparedStatements);
        properties.put("maxPoolPreparedStatementPerConnectionSize", this.maxPoolPreparedStatementPerConnectionSize);
        properties.put("validationQuery", getValidateQueryByUrl(this.url));
        properties.put("testOnBorrow", this.testOnBorrow);
        properties.put("testOnReturn", this.testOnReturn);
        properties.put("testWhileIdle", this.testWhileIdle);
        properties.put("timeBetweenEvictionRunsMillis", this.timeBetweenEvictionRunsMillis);
        properties.put("minEvictableIdleTimeMillis", this.minEvictableIdleTimeMillis);
        properties.put("filters", this.filters);
        properties.put("connectionProperties",this.connectionProperties);
        return properties;
    }

    private String getValidateQueryByUrl(String url) {
        if (url.contains("oracle")) {
            return "select 1 from dual";
        } else if (url.contains("postgresql")) {
            return "select version()";
        } else if (url.contains("sqlserver")) {
            return "select 1";
        } else {
            return "select 1";
        }
    }

}
