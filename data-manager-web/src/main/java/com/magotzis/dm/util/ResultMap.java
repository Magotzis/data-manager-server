package com.magotzis.dm.util;

import com.magotzis.dm.component.pageHelper.Page;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author dengyq on 16:34 2018/3/5
 */
public class ResultMap extends LinkedHashMap<String, Object> {
    public ResultMap() {}

    public ResultMap(String attributeName, Object attributeValue) {
        this.addAttribute(attributeName, attributeValue);
    }

    public ResultMap(Object attributeValue) {
        this.addAttribute(attributeValue);
    }

    public ResultMap addAttribute(String name, Object object) {
        Assert.notNull(name, "Model attribute name must not be null");
        this.put(name, object);
        return this;
    }

    private void addAttribute(Object object) {
        Assert.notNull(object, "Model object must not be null");
        if (!(object instanceof Collection) || !((Collection) object).isEmpty()) {
            this.addAttribute(Conventions.getVariableName(object), object);
        }
    }

    public ResultMap success(Object data) {
        return (new ResultMap()).addAttribute("statusCode", 200).addAttribute("message", "success").addAttribute("data", data);
    }

    public ResultMap success() {
        return (new ResultMap()).addAttribute("statusCode", 200).addAttribute("message", "success");
    }

    public ResultMap page(Page<?> page) {
        this.addAttribute("statusCode", 200);
        this.addAttribute("message", "success");
        this.addAttribute("draw", page.getDraw());
        this.addAttribute("data", page.getData());
        this.addAttribute("recordsTotal", page.getRecordsTotal());
        this.addAttribute("recordsFiltered", page.getRecordsFiltered());
        return this;
    }
}
