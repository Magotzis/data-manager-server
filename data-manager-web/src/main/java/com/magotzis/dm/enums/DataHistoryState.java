package com.magotzis.dm.enums;

/**
 * @author dengyq on 11:33 2018/4/11
 */
public enum DataHistoryState {
    SUCCESS(0), FAIL(1);

    private int state;

    DataHistoryState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
