package com.magotzis.dm.api.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.magotzis.dm.api.service.ExampleApiService;

/**
 * @author dengyq on 14:55 2018/2/7
 */
@Service
public class ExampleApiServiceImpl implements ExampleApiService{
    @Override
    public void exampleFunc() {
        System.out.println("Hello World!");
    }
}
