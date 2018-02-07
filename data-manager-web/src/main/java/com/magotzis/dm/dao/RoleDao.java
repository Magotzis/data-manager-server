package com.magotzis.dm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author dengyq on 11:31 2018/1/16
 */
@Repository
public interface RoleDao {
    String getById(@PathVariable("id") int id);
}
