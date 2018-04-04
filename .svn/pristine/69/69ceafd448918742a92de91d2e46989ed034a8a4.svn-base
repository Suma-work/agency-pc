package com.sumainfo.modules.oss.cloud;


import com.sumainfo.common.utils.ConfigConstant;
import com.sumainfo.common.utils.Constant;
import com.sumainfo.common.utils.SpringContextUtils;
import com.sumainfo.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
* @Title: OSSFactory.java 
* @Package com.sumainfo.modules.oss.cloud  
* @author zhlu
* @date 2018年3月15日
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
