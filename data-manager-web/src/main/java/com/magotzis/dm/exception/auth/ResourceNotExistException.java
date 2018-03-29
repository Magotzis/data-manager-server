package com.magotzis.dm.exception.auth;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 17:09 2018/3/28
 */
public class ResourceNotExistException extends BaseException {

    public ResourceNotExistException(String message) {
        super(message);
    }

    public ResourceNotExistException() {
        super("资源不存在");
    }
}
