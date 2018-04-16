package com.magotzis.dm.dao;

import com.magotzis.dm.api.dto.DataSourceDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 17:07 2018/4/16
 */
@Repository
public interface CommonDao {

    List<DataSourceDto> getDataSourceList();
}
