package com.github.zrpc.container.mock;

import com.github.zrpc.container.mock.ConsumerService;
import com.github.zrpc.container.mock.ConsumerServiceImpl;
import com.github.zrpc.container.mock.ProducerService;
import com.github.zrpc.container.mock.ProducerServiceImpl;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * service container fill with published service
 * @author Zer01ne
 * @since 2020/1/20 13:53
 */
public class ServiceContainer {

    private static Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    public void publishService(Class clazz, Object impl) {
        serviceMap.put(clazz.getName(), impl);
    }

    public static Map<String, Object> getServiceMap() {
        return Collections.unmodifiableMap(serviceMap);
    }

    private ServiceContainer() {}

    public void mockService() {
        this.publishService(ProducerService.class, new ProducerServiceImpl());
        this.publishService(ConsumerService.class, new ConsumerServiceImpl());
    }

}