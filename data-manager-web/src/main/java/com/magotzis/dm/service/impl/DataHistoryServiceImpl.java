package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.exception.data.SqlExecuteFailException;
import com.magotzis.dm.api.service.DataManagementApiService;
import com.magotzis.dm.dao.DataHistoryDao;
import com.magotzis.dm.enums.DataHistoryState;
import com.magotzis.dm.enums.DataHistoryType;
import com.magotzis.dm.exception.data.FileDownloadFailException;
import com.magotzis.dm.model.DataHistory;
import com.magotzis.dm.service.DataHistoryService;
import com.magotzis.dm.vo.DataHistoryVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author dengyq on 10:36 2018/4/10
 */
@Service
public class DataHistoryServiceImpl implements DataHistoryService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Reference
    private DataManagementApiService dataManagementApiService;

    @Resource
    private DataHistoryDao importHistoryDao;

    @Override
    public List<DataHistoryVo> getList(int type) {
        return importHistoryDao.getList(type);
    }

    @Override
    public DataHistory getById(int id, int type) {
        return importHistoryDao.findById(id, type);
    }

    @Override
    public void importData(DataHistory dataHistory) {
        try {
            dataManagementApiService.importData(dataHistory.getContent());
            dataHistory.setState(DataHistoryState.SUCCESS.getState());
            dataHistory.setErrorMsg(null);
        } catch (SqlExecuteFailException e) {
            dataHistory.setState(DataHistoryState.FAIL.getState());
            dataHistory.setErrorMsg(e.getMessage());
        }
        dataHistory.setType(DataHistoryType.IMPORT.getType());
        dataHistory.setUpdateTime(new Date());
        importHistoryDao.insert(dataHistory);
    }

    @Override
    public void exportData(DataHistory dataHistory, HttpServletResponse response) {
        String path = "";
        try {
            path = dataManagementApiService.exportData(dataHistory.getContent());
            dataHistory.setState(DataHistoryState.SUCCESS.getState());
            dataHistory.setErrorMsg(null);
        } catch (SqlExecuteFailException e) {
            dataHistory.setState(DataHistoryState.FAIL.getState());
            dataHistory.setErrorMsg(e.getMessage());
        }
        dataHistory.setType(DataHistoryType.EXPORT.getType());
        dataHistory.setUpdateTime(new Date());
        importHistoryDao.insert(dataHistory);
        if (StringUtils.isNotEmpty(path)) {
            String fileName = path.substring(path.lastIndexOf("/") + 1);
            try {
                response.setContentType("application/octet-stream");
                response.setContentType("application/OCTET-STREAM;charset=UTF-8");
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                InputStream in = new FileInputStream(path);
                response.setContentLength(in.available());
                OutputStream out = response.getOutputStream();
                byte[] b = new byte[1024];
                int len;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                out.flush();
                out.close();
                in.close();
            } catch (Exception e) {
                LOGGER.info("下载文件失败");
                throw new FileDownloadFailException();
            }
        }
    }
}
