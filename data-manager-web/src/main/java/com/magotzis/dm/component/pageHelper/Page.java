package com.magotzis.dm.component.pageHelper;

import java.util.List;

/**
 * @author dengyq on 11:19 2018/2/28
 */
public class Page<T> {

    /**
     * 绘制计数器。这个是dataTables用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private int draw;

    /**
     * 总记录数
     */
    private Long recordsTotal = 0L;

    /**
     * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    private Long recordsFiltered = 0L;

    /**
     * 分页的数据?
     */
    private List<T> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
