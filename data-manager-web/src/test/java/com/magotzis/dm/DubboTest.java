package com.magotzis.dm;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.service.ExampleApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dengyq on 16:09 2018/2/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboTest {

    @Reference
    private ExampleApiService exampleApiService;

    @Test
    public void RedisTests(){
        exampleApiService.exampleFunc();
    }
}
