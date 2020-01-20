package com.github.zrpc.container.mock;

/**
 * @author Zer01ne
 * @since 2020/1/20 0:00
 */
public class ProducerServiceImpl implements ProducerService {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}