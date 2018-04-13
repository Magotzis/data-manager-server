package com.magotzis.dm.exception.data;

import com.magotzis.dm.exception.BaseException;

/**
 * @author dengyq on 13:48 2018/4/13
 */
public class FileDownloadFailException extends BaseException {

    public FileDownloadFailException(String message) {
        super(message);
    }

    public FileDownloadFailException() {
        super("文件下载失败");
    }
}
