package org.bigant.wf.lark.springboot;

import com.lark.oapi.Client;
import org.bigant.fw.lark.LarkCallback;
import org.bigant.fw.lark.LarkConfig;
import org.bigant.fw.lark.instances.LarkInstancesService;
import org.bigant.fw.lark.process.LarkProcessService;
import org.bigant.wf.Factory;
import org.bigant.wf.cache.ICache;
import org.bigant.wf.cache.LocalCache;
import org.bigant.wf.instances.InstancesAction;
import org.bigant.wf.user.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * spring boot 自动配置
 *
 * @author galen
 * @date 2024/2/2316:42
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LarkProperties.class)
public class WfLarkSpringBootAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public ICache wfCache() {
        return LocalCache.getInstance();
    }

    @Bean
    @ConditionalOnMissingBean
    public LarkConfig larkConfig(LarkProperties larkProperties) {

        LarkConfig larkConfig = new LarkConfig(larkProperties.getAppId()
                , larkProperties.getAppSecret()
                , larkProperties.getEncryptKey());

        larkConfig.setClient(Client.newBuilder(larkProperties.getAppId(), larkProperties.getAppSecret())
                .openBaseUrl(larkProperties.getOpenBaseUrl()) // 设置域名，默认为飞书
                .requestTimeout(larkProperties.getRequestTimeout(), TimeUnit.SECONDS) // 设置httpclient 超时时间，默认永不超时
                .logReqAtDebug(larkProperties.isLogDebug()) // 在 debug 模式下会打印 http 请求和响应的 headers,body 等信息。
                .build());

        return larkConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(InstancesAction.class)
    public LarkCallback larkCallback(InstancesAction instancesAction) {
        return new LarkCallback(instancesAction);
    }

    @Bean
    @ConditionalOnMissingBean
    public LarkProcessService larkProcessService(LarkConfig larkConfig) {
        LarkProcessService larkProcessService = new LarkProcessService(larkConfig);
        Factory.registerProcessService(larkProcessService.getType(), larkProcessService);
        return larkProcessService;
    }

    @Bean
    @ConditionalOnMissingBean
    public LarkInstancesService larkInstancesService(LarkConfig larkConfig, LarkProcessService larkProcessService, UserService userService) {
        LarkInstancesService larkInstancesService = new LarkInstancesService(larkConfig, larkProcessService, userService);
        Factory.registerInstancesService(larkInstancesService.getType(), larkInstancesService);
        return larkInstancesService;
    }


}