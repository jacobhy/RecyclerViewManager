package com.jacob.www.recyclerviewmanager.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jacob.www.recyclerviewmanager.bean.BaseTemplateBean;


/**
 * @classDescription: BaseViewHolder
 * @author: JacobYu,on 2018/12/20
 */
public  class BaseViewHolder<T extends BaseTemplateBean> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    // 整体刷新绑定数据
    public  void showData(T data){

    }

    // 局部刷新绑定数据
    public void refreshData(T data) {
    }
}
