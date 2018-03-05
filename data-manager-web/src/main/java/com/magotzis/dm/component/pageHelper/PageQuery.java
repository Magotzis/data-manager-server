package com.magotzis.dm.component.pageHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dengyq on 11:39 2018/2/28
 */
public class PageQuery {
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final boolean DEFAULT_REQUIRE_TOTAL_COUNT = true;

    /**
     * 绘制计数器。这个是dataTables用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private int draw;
    /**
     * 第几页，首页为1
     */
    private Integer pageNum;
    /**
     * 每页记录条数
     */
    private Integer pageSize;
    /**
     * 是否需要记录总数
     */
    private Boolean requireTotalCount;

    public PageQuery(HttpServletRequest request) {
        //开始的数据行数
        String start = request.getParameter("start");
        //每页的数据数
        String length = request.getParameter("length");
        //DT传递的draw:
        String draw = request.getParameter("draw");

        this.setDraw(Integer.parseInt(draw));
        try {
            this.pageSize = Integer.parseInt(length);
        }catch (NumberFormatException e){
            this.pageSize = 10;
        }
        try {
            this.pageNum = (Integer.parseInt(start) / Integer.parseInt(length)) + 1;
        }catch (NumberFormatException e){
            this.pageNum = 1;
        }
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getRequireTotalCount() {
        return requireTotalCount;
    }

    public void setRequireTotalCount(Boolean requireTotalCount) {
        this.requireTotalCount = requireTotalCount;
    }
}
