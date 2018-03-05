package com.magotzis.dm.component.pageHelper;

import java.util.List;

/**
 * @author dengyq on 11:32 2018/2/28
 */
public interface PageCallBack {
    <T> List<T> select();
}
