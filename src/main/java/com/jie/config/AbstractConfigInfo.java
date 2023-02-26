package com.jie.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.Executor;


public abstract class AbstractConfigInfo<T> implements InitializingBean, Listener {

    @NacosInjected
    protected ConfigService configService;

    protected T data;

    public T getData() {
        return data;
    }

    public Executor getExecutor() {
        return null;
    }

    public void receiveConfigInfo(String configInfo) {
        System.out.println(configInfo);
        compile(configInfo);
    }

    public void afterPropertiesSet() throws RuntimeException {
        try {
            String configInfo = configService.getConfig(getDataId(), getGroupId(), getTimeout());
            compile(configInfo);
            System.out.println(configInfo);
            configService.addListener(getDataId(), getGroupId(), this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取nacos dataId
     *
     * @return nacos dataId
     */
    protected abstract String getDataId();

    /**
     * 获取nacos groupId
     * <p>默认NacosConstant.GROUP_ID</p>
     *
     * @return nacos groupId
     */
    protected abstract String getGroupId();

    /**
     * 获取nacos 超时时间
     * <p>默认NacosConstant.TIMEOUT</p>
     *
     * @return nacos 超时时间
     */
    protected long getTimeout() {
        return 6000;
    }

    /**
     * 将输入转为数据
     *
     * @param dataStr
     */
    protected abstract void compile(String dataStr);
}
