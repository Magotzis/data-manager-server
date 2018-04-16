package com.magotzis.dm;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dengyq on 14:57 2018/2/7
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.magotzis.dm.dao")
@DubboComponentScan("com.magotzis.dm.api.service")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
