package com.magotzis.dm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.magotzis.dm.api.dto.AnalysisDto;
import com.magotzis.dm.api.service.AnalysisApiService;
import com.magotzis.dm.exception.analysis.DateErrorException;
import com.magotzis.dm.exception.user.UserNotExistException;
import com.magotzis.dm.model.User;
import com.magotzis.dm.service.AnalysisService;
import com.magotzis.dm.service.UserService;
import com.magotzis.dm.vo.AnalysisVo;
import com.magotzis.dm.vo.DataSourcesAnalysisVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dengyq on 17:49 2018/4/20
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Reference
    private AnalysisApiService analysisApiService;

    @Resource
    private UserService userService;

    @Override
    public List<AnalysisDto> getFullDataSourcesAnalysis() {
        return analysisApiService.getFullDataSourcesAnalysis();
    }

    @Override
    public DataSourcesAnalysisVo getDataSourcesAnalysis(String dataSources, String begin, String end) {
        DataSourcesAnalysisVo dataSourcesAnalysisVo = new DataSourcesAnalysisVo();
        // 处理时间
        int type = timeHandle(dataSourcesAnalysisVo, begin, end);
        // 设置数据源
        List<String> dataSourceList = Arrays.asList(dataSources.split("-"));
        dataSourcesAnalysisVo.setAnalysisVos(getAnalysisVoList(dataSourceList, type, dataSourcesAnalysisVo.getAxis()));
        return dataSourcesAnalysisVo;
    }

    @Override
    public DataSourcesAnalysisVo getUserRecordAnalysis(String username, String begin, String end) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException();
        }
        DataSourcesAnalysisVo dataSourcesAnalysisVo = new DataSourcesAnalysisVo();
        // 处理时间
        int type = timeHandle(dataSourcesAnalysisVo, begin, end);
        // 设置数据源
        dataSourcesAnalysisVo.setAnalysisVos(getAnalysisVoList(username, type, dataSourcesAnalysisVo.getAxis()));
        return dataSourcesAnalysisVo;
    }

    private List<AnalysisVo> getAnalysisVoList(List<String> dataSourceList, int type, List<String> xAxis) {
        List<AnalysisVo> analysisVoList = new ArrayList<>(dataSourceList.size());
        dataSourceList.forEach(d -> {
            AnalysisVo analysisVo = new AnalysisVo();
            analysisVo.setName(d);
            List<Integer> list = new ArrayList<>();
            for (String aX : xAxis) {
                list.add(analysisApiService.getDataSourceNum(d, type, aX));
            }
            analysisVo.setList(list);
            analysisVoList.add(analysisVo);
        });
        return analysisVoList;
    }

    private List<AnalysisVo> getAnalysisVoList(String username, int type, List<String> xAxis) {
        List<AnalysisVo> analysisVoList = new ArrayList<>();
        AnalysisVo analysisVo = new AnalysisVo();
        analysisVo.setName(username);
        List<Integer> list = new ArrayList<>();
        for (String aX : xAxis) {
            list.add(analysisApiService.getUserRecordNum(username, type, aX));
        }
        analysisVo.setList(list);
        analysisVoList.add(analysisVo);
        return analysisVoList;
    }

    private int timeHandle(DataSourcesAnalysisVo dataSourcesAnalysisVo, String begin, String end) {
        try {
            /*
              转换时间
             */
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date beginTime = formatter.parse(begin);
            Date endTime = formatter.parse(end);
            if (beginTime.after(endTime)) {
                throw new DateErrorException("开始时间不能晚于结束时间");
            }
            Calendar beginCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            beginCalendar.setTime(beginTime);
            endCalendar.setTime(endTime);
            // 设置时间轴
            List<String> xAxis = new ArrayList<>();
            dataSourcesAnalysisVo.setAxis(xAxis);
            return decideViewType(beginCalendar, endCalendar, xAxis);
        } catch (ParseException e) {
            LOGGER.info("日期转换失败");
            throw new DateErrorException("时间格式错误，请输入正确的时间");
        }
    }

    private int decideViewType(Calendar beginCalendar, Calendar endCalendar, List<String> xAxis) {
        int YEAR = 1, MONTH = 2, DAY = 3, HOUR = 4, MINUTE = 5;
        String time = "";
        if (beginCalendar.get(Calendar.YEAR) != endCalendar.get(Calendar.YEAR)) {
            int year = beginCalendar.get(Calendar.YEAR);
            while (year <= endCalendar.get(Calendar.YEAR)) {
                xAxis.add(String.valueOf(year++));
            }
            return YEAR;
        }
        time += beginCalendar.get(Calendar.YEAR) + "-";
        if (beginCalendar.get(Calendar.MONTH) != endCalendar.get(Calendar.MONTH)) {
            int month = beginCalendar.get(Calendar.MONTH) + 1;
            while (month <= endCalendar.get(Calendar.MONTH) + 1) {
                if (month < 10) {
                    xAxis.add(time + "0" + month++);
                } else {
                    xAxis.add(time + month++);
                }
            }
            return MONTH;
        }
        if (beginCalendar.get(Calendar.MONTH) + 1 < 10) {
            time += "0" + (beginCalendar.get(Calendar.MONTH) + 1) + "-";
        } else {
            time += (beginCalendar.get(Calendar.MONTH) + 1) + "-";
        }
        if (beginCalendar.get(Calendar.DAY_OF_MONTH) != endCalendar.get(Calendar.DAY_OF_MONTH)) {
            int day = beginCalendar.get(Calendar.DAY_OF_MONTH);
            while (day <= endCalendar.get(Calendar.DAY_OF_MONTH)) {
                if (day < 10) {
                    xAxis.add(time + "0" + day++);
                } else {
                    xAxis.add(time + day++);
                }
            }
            return DAY;
        }
        if (beginCalendar.get(Calendar.DAY_OF_MONTH) < 10) {
            time += "0" + beginCalendar.get(Calendar.DAY_OF_MONTH) + " ";
        } else {
            time += beginCalendar.get(Calendar.DAY_OF_MONTH) + " ";
        }
        if (beginCalendar.get(Calendar.HOUR_OF_DAY) != endCalendar.get(Calendar.HOUR_OF_DAY)) {
            int hour = beginCalendar.get(Calendar.HOUR_OF_DAY);
            while (hour <= endCalendar.get(Calendar.HOUR_OF_DAY)) {
                if (hour < 10) {
                    xAxis.add(time + "0" + hour++);
                } else {
                    xAxis.add(time + hour++);
                }
            }
            return HOUR;
        }
        if (beginCalendar.get(Calendar.HOUR_OF_DAY) < 10) {
            time += "0" + beginCalendar.get(Calendar.HOUR_OF_DAY) + ":";
        } else {
            time += beginCalendar.get(Calendar.HOUR_OF_DAY) + ":";
        }
        int minute = beginCalendar.get(Calendar.MINUTE);
        while (minute <= endCalendar.get(Calendar.MINUTE)) {
            if (minute < 10) {
                xAxis.add(time + "0" + minute++);
            } else {
                xAxis.add(time + minute++);
            }
        }
        return MINUTE;
    }
}
