package com.magotzis.dm;

import com.magotzis.dm.api.service.DataManagementApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataManagementApiServiceTest {

    @Resource
    private DataManagementApiService dataManagementApiService;

    @Test
    public void testImportFromSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into `role`(role) values('test1');")
                .append("insert into `role`(role) values('test2');")
                .append("insert into `role`(role) values('test3');");
        dataManagementApiService.importData(stringBuilder.toString());
    }
}
