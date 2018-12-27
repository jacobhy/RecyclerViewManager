package com.jacob.www.recyclerviewmanager.bean;

import java.io.Serializable;


/**
 * @classDescription: 模板基础数据bean
 * @author: JacobYu, on 2018/12/20
 */
public class BaseTemplateBean implements Serializable {

    private static final long serialVersionUID = 6420587556201330392L;

    // 模板id：一种ui类型对应一种模板
    public int viewType;

    // 模板分组id
    public int groupId;

    public BaseTemplateBean(int viewType) {
        this.viewType = viewType;
    }

    public BaseTemplateBean(int viewType, int groupId) {
        this(viewType);
        this.groupId = groupId;
    }

    /**
     * 设置模板id
     *
     * @param viewType
     */
    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    /**
     * 模板分组id
     *
     * @param groupId
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
