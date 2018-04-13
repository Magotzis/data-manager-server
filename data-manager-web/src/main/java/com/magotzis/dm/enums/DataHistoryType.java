package com.magotzis.dm.enums;

/**
 * @author dengyq on 11:10 2018/4/13
 */
public enum DataHistoryType {
    IMPORT(0), EXPORT(1);

    private int type;

    DataHistoryType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
