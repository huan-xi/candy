package com.huanxi.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.log4j.Logger;

import java.io.*;

public class AliOSSUtil {
    private final static Logger logger = Logger.getLogger(AliOSSUtil.class);

    /**
     * 管理控制台里面获取EndPoint
     */
    private final static String END_POINT = "oss-cn-qingdao.aliyuncs.com";
    /**
     * 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
     */
    private final static String ACCESS_KEY_ID = "LTAIHWS1KXcVML3v";
    private final static String ACCESS_KEY_SECRET = "Mjrxbz28Z07nExlPd0beTr0tZx6Kj4";
    /**
     * 上传的BUCKET名称
     */
    private final static String BUCKET_NAME = "huanxi-candy";
    /**
     * 管理控制台里面获取的访问域名
     */
    private final static String FILE_HOST = "huanxi-candy.oss-cn-qingdao.aliyuncs.com";

    /**
     * 上传文件到bucket
     *
     * @param file     本地文件
     * @param dir      bucket存放目录(末尾要加/)
     * @param fileName 上传文件名
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir, String fileName) {
        if (file == null || !file.exists()) {
            return null;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, dir + fileName, file));
            if (null != result) {
                return FILE_HOST + dir + fileName;
            } else {
                return null;
            }
        } catch (Exception oe) {
            logger.error("上传OSS失败:", oe);
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }

    /**
     *上传文件到bucket,
     * @param fileInputStream
     * @param fileName
     * @return
     */
    public static String uploadLocalFile(FileInputStream fileInputStream,String fileName,String ossPath) throws IOException {
        String path="D:/uploadTemp";
        File path_file = new File(path); //临时目录
        File file=new File(path + File.separator + fileName);
        if (!path_file.exists()) {
            path_file.mkdirs();
        }
        //保存文件到临时目录
        BufferedOutputStream bos = null;
        try {

            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        //上传文件到oss
        uploadLocalFile(file, ossPath);
        return ossPath+fileName;
    }

    /**
     * 上传文件到bucket
     *
     * @param file 本地文件
     * @param dir  bucket目录
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir) {
        if (file == null) {
            return null;
        }
        String filePath = dir + file.getName();
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, filePath, file));
            if (null != result) {
                // 拼装访问地址
                return FILE_HOST + filePath;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            logger.error("OSS上传失败:", oe);
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }
}
