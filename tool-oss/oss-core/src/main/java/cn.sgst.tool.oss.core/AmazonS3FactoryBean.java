package cn.sgst.tool.oss.core;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/29 14:41
 */

public class AmazonS3FactoryBean implements FactoryBean<AmazonS3>,InitializingBean,DisposableBean {

    /** your accessKey */
    private String accessKey;
    /** your secretKey */
    private String secretKey;
    /** your endPoint start with http or https. default https */
    private String endPoint;
    /** oss数据中心所在的物理位置 */
    private String region;

    private AmazonS3 amazonS3;


    @Override
    public void destroy() throws Exception {
    }

    @Override
    public AmazonS3 getObject() throws Exception {
        return amazonS3;
    }

    @Override
    public Class<?> getObjectType() {
        return AmazonS3.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.hasText(accessKey,"'accessKey' must not be empty");
        Assert.hasText(secretKey,"'secretKey' must not be empty");
        Assert.hasText(endPoint,"'endPoint' must not be empty");
        amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(endPoint, region == null ? "" : region)
                ).build();
    }


    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
