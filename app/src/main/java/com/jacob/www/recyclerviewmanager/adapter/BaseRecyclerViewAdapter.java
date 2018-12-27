package com.jacob.www.recyclerviewmanager.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * @classDescription: BaseAdapter
 * @author: JacobYu, on 2018/12/20
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> mDatas;

    /**
     * 设置数据
     *
     * @param datas
     */
    public void setDatas(List<? extends T> datas) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.clear();
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addDatas(List<? extends T> datas) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param position
     * @param datas
     */
    public void addDatas(int position, List<? extends T> datas) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.addAll(position, datas);
        notifyItemRangeInserted(position, datas.size());
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(T data) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(data);
        notifyItemInserted(mDatas.size() - 1);
    }

    /**
     * 添加数据
     *
     * @param position
     * @param data
     */
    public void addData(int position, T data) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     *
     * @param position
     */
    public boolean removeData(int position) {
        if (mDatas != null && position >= 0 && position < mDatas.size()) {
            mDatas.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    /**
     * 删除所有数据
     */
    public void clearDatas() {
        if (mDatas != null) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据
     */
    public List<T> getDatas() {
        return mDatas;
    }

    /**
     * 获取数据数量
     */
    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }
}
