package com.magotzis.dm.exception.user;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 11:24 2018/3/30
 */
public class UsernameExistException extends BaseException {
    public UsernameExistException() {
        super("用户名已经存在");
    }

    public UsernameExistException(String message) {
        super(message);
    }
}
