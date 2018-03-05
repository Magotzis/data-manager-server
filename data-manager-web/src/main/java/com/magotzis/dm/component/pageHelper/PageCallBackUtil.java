package com.magotzis.dm.component.pageHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author dengyq on 11:35 2018/2/28
 */
public class PageCallBackUtil {
    /**
     * 封装公共PageHelper的操作
     */
    public static<T> Page<T> selectPage(PageQuery query, PageCallBack callBack){
        Assert.notNull(query, "qry can't be null!");
        Assert.notNull(callBack, "callBack cant' be null!");
        setPageHelperStartPage(query);

        List<T> list = callBack.select();
        Page<T> page = new Page<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        page.setDraw(query.getDraw());
        page.setRecordsTotal(pageInfo.getTotal());
        page.setRecordsFiltered(pageInfo.getTotal());
        page.setData(pageInfo.getList());
        return page;
    }

    /**
     * 设置PageHelper的startPage
     */
    private static void setPageHelperStartPage(PageQuery query) {
        // 设置分页信息
        // pageNum
        Integer pageNum = query.getPageNum();
        pageNum = pageNum == null? PageQuery.DEFAULT_PAGE_NUM : pageNum;
        // pageSize
        Integer pageSize = query.getPageSize();
        pageSize = pageSize == null ? PageQuery.DEFAULT_PAGE_SIZE : pageSize;
        // requireTotalCount
        Boolean requireTotalCount = query.getRequireTotalCount();
        requireTotalCount = requireTotalCount == null ? PageQuery.DEFAULT_REQUIRE_TOTAL_COUNT : requireTotalCount;
        PageHelper.startPage(pageNum, pageSize,requireTotalCount);
    }
}
