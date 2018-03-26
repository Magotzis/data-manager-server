package com.magotzis.dm.exception.user;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 17:11 2018/3/5
 */
public class UserNotExistException extends BaseException {
    public UserNotExistException(String message) {
        super(message);
    }

    public UserNotExistException() {
        super("用户不存在");
    }
}
