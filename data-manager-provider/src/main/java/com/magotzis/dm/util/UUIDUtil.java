package com.magotzis.dm.util;

import java.util.UUID;

/**
 * @author dengyq on 11:49 2018/4/12
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
