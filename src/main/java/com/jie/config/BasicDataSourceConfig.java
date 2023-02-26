package com.jie.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class BasicDataSourceConfig extends AbstractConfigInfo<DataSourceVO> {

    @Override
    protected String getDataId() {
        return "basic_datasource";
    }

    @Override
    protected void compile(String dataStr) {
        data = JSONObject.parseObject(dataStr, DataSourceVO.class);
    }

    @Override
    protected String getGroupId() {
        return "zk_study";
    }

    @Override
    public void afterPropertiesSet() throws RuntimeException {
        try {
            String configInfo = configService.getConfig(getDataId(), getGroupId(), getTimeout());
            compile(configInfo);
            configService.addListener(getDataId(), getGroupId(), this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
