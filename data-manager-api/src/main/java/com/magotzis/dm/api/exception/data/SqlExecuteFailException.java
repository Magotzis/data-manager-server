package com.magotzis.dm.api.exception.data;

import com.magotzis.dm.api.exception.BaseException;

/**
 * @author dengyq on 11:43 2018/4/11
 */
public class SqlExecuteFailException extends BaseException {
    public SqlExecuteFailException(String message) {
        super(message);
    }

    public SqlExecuteFailException() {
        super("sql执行错误");
    }
}
