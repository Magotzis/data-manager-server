package com.magotzis.dm.service.impl;

import com.magotzis.dm.api.dto.DataSourceDto;
import com.magotzis.dm.api.exception.data.SqlExecuteFailException;
import com.magotzis.dm.dao.CommonDao;
import com.magotzis.dm.service.DataManagementService;
import com.magotzis.dm.util.UUIDUtil;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static com.alibaba.druid.util.JdbcUtils.close;

@Service
public class DataManagementServiceImpl implements DataManagementService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Resource
    private CommonDao commonDao;

    @Override
    public boolean importData(File file) {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setAutoCommit(true);//自动提交
            runner.setFullLineDelimiter(false);
            runner.setDelimiter(";");////每条命令间的分隔符
            runner.setSendFullScript(false);
            runner.setStopOnError(false);
            runner.runScript(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (Exception e) {
            LOGGER.error("执行sql文件失败", e);
            return false;
        } finally {
            close(conn);
        }
        return true;
    }

    @Override
    public void importData(String sql) {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            String[] sqls = sql.split(";");
            for (String s : sqls) {
                statement.addBatch(s);
            }
            statement.executeBatch();
        } catch (Exception e) {
            LOGGER.error("执行sql失败", e);
            throw new SqlExecuteFailException(e.getMessage());
        } finally {
            close(conn);
        }
    }

    @Override
    public String exportData(String sql) {
        Connection conn = null;
        String fileName ="/var/lib/mysql-files/" + UUIDUtil.getUUID() + ".xls";
        try {
            String exeSql = sql + " into outfile '" + fileName + "'";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            statement.execute(exeSql);
        } catch (Exception e) {
            LOGGER.error("执行sql失败", e);
            throw new SqlExecuteFailException(e.getMessage());
        } finally {
            close(conn);
        }
        return fileName;
    }

    @Override
    public List<DataSourceDto> getDataSourceList() {
        return commonDao.getDataSourceList();
    }
}
