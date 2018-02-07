package com.magotzis.dm.dao;

import com.magotzis.dm.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengyq on 10:44 2018/1/18
 */
@Repository
public interface ResourceDao {

    List<Resource> listResourceWithRoles();
}
