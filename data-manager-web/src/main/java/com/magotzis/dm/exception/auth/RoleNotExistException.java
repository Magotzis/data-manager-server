package com.magotzis.dm.exception.auth;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 11:04 2018/3/27
 */
public class RoleNotExistException extends BaseException {

    public RoleNotExistException(String message) {
        super(message);
    }

    public RoleNotExistException() {
        super("角色不存在");
    }
}
