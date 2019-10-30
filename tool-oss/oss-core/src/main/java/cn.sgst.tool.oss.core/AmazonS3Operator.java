package cn.sgst.tool.oss.core;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

/*
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/10/29 10:33
 */
public interface AmazonS3Operator {

        /**
         * 获取用户所拥有的存储桶的列表
         * @author fli
         * @date 2019/10/29 10:59
         */
        List<Bucket> listBuckets();

        /**
         * 创建一个桶
         * @author fli
         * @date 2019/10/29 11:05
         */
        Bucket createBucket(String bucketName);

        /**
         * 删除一个桶
         * @author fli
         * @date 2019/10/29 11:07
         */
        void deleteBucket(String bucketName);

        /**
         *
         * 列出指定桶中所有对象
         * @author fli
         * @date 2019/10/29 11:29
         */
        List<S3ObjectSummary> listObjects(String bucketName);

        /**
         * 在指定桶中上传一个对象
         * 上传者必须对指定bucket有写权限
         * 桶里有同名文件，则会覆盖原有文件
         * @author fli
         * @date 2019/10/29 11:13
         */
        void putObject(String bucketName, String key, byte[] buf);

        /**
         * 删除桶中指定的对象
         * @author fli
         * @date 2019/10/29 11:24
         */
        void deleteObject(String bucketName, String key);
}
