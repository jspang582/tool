package cn.sgst.tool.oss.core;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/29 10:33
 */
public class SampleAmazonS3Operator implements AmazonS3Operator {

    private AmazonS3 conn;


    public SampleAmazonS3Operator(AmazonS3 conn) {
        Assert.notNull(conn,"AmazonS3 special must not be null");
        this.conn = conn;
    }


    @Override
    public List<Bucket> listBuckets() {
        return conn.listBuckets();
    }

    @Override
    public Bucket createBucket(String bucketName) {
        return conn.createBucket(bucketName);
    }

    @Override
    public void deleteBucket(String bucketName) {
        conn.deleteBucket(bucketName);
    }

    @Override
    public List<S3ObjectSummary> listObjects(String bucketName) {
        return conn.listObjects(bucketName).getObjectSummaries();
    }

    @Override
    public void putObject(String bucketName, String key, byte[] buf) {
        conn.putObject(bucketName,key,new ByteArrayInputStream(buf),new ObjectMetadata());
    }

    @Override
    public void deleteObject(String bucketName, String key) {
        conn.deleteObject(bucketName,key);
    }


}
