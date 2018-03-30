package com.magotzis.dm.exception.user;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 11:24 2018/3/30
 */
public class EmailExistException extends BaseException {
    public EmailExistException() {
        super("email已经存在");
    }

    public EmailExistException(String message) {
        super(message);
    }
}
