package com.magotzis.dm.dao;

import com.magotzis.dm.model.DataHistory;
import com.magotzis.dm.vo.DataHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 10:35 2018/4/10
 */
@Repository
public interface DataHistoryDao {
    List<DataHistoryVo> getList(@Param("type") int type);

    DataHistory findById(@Param("id") int id, @Param("type") int type);

    void insert(DataHistory dataHistory);
}
