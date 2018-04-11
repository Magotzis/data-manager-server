package com.magotzis.dm.dao;

import com.magotzis.dm.model.ImportHistory;
import com.magotzis.dm.vo.ImportHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 10:35 2018/4/10
 */
@Repository
public interface ImportHistoryDao {
    List<ImportHistoryVo> getList();

    ImportHistory findById(@Param("id") int id);

    void insert(ImportHistory importHistory);
}
