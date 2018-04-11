package com.magotzis.dm.enums;

/**
 * @author dengyq on 11:33 2018/4/11
 */
public enum ImportHistoryState {
    SUCCESS(0), FAIL(1);

    private int state;

    ImportHistoryState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
