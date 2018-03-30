package com.magotzis.dm.exception.auth;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 11:21 2018/3/30
 */
public class RepasswordNotEqualsException extends BaseException {
    public RepasswordNotEqualsException(String message) {
        super(message);
    }

    public RepasswordNotEqualsException() {
        super("重复密码与密码不一致");
    }
}
