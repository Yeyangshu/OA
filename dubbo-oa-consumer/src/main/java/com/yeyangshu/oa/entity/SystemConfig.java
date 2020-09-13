package com.yeyangshu.oa.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置类
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/29 19:50
 */
@Component
public class SystemConfig {

    /** 取配置文件中的数据 */
    @Value(value = "${systemConfig.systemName}")
    private String systemName;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
