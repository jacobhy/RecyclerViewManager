package com.jacob.www.recyclerviewmanager.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacob.www.recyclerviewmanager.R;
import com.jacob.www.recyclerviewmanager.bean.BaseTemplateBean;
import com.jacob.www.recyclerviewmanager.viewholder.BaseViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.jacob.www.recyclerviewmanager.utils.TemplateIdConstant.TEMPLATE_BOTTOM;
import static com.jacob.www.recyclerviewmanager.utils.TemplateIdConstant.TEMPLATE_DEFAULT;
import static com.jacob.www.recyclerviewmanager.utils.TemplateIdConstant.TEMPLATE_HEAD;


public class CustomRVAdapter extends BaseRecyclerViewAdapter<BaseTemplateBean, BaseViewHolder<BaseTemplateBean>> {

    // 指定的Type对应的ViewGroup
    private SparseArray<Integer> mLayoutIdMap;

    // 指定的Type对应的ViewHolder
    private SparseArray<Class<?>> mViewHolderMap;

    // 头部数据
    private BaseTemplateBean mHeadBean = null;

    // 底部数据
    private BaseTemplateBean mBottomBean = null;

    public CustomRVAdapter() {
        mLayoutIdMap = new SparseArray<>();
        mViewHolderMap = new SparseArray<>();
    }

    public void addItem(int viewType, int layoutId, Class viewHolder) {
        if (mLayoutIdMap == null || mViewHolderMap == null) {
            throw new NullPointerException("mLayoutIdMap must not null or mViewHolderMap must not null");
        }
        mLayoutIdMap.put(viewType, layoutId);
        mViewHolderMap.put(viewType, viewHolder);
        if (mLayoutIdMap.size() != mViewHolderMap.size()) {
            throw new IllegalArgumentException("mLayoutIdMap'size != mViewHolderMap'size");
        }
    }

    // 添加默认主内容模板
    public void addItem(int layoutId, Class viewHolder) {
        addItem(TEMPLATE_DEFAULT, layoutId, viewHolder);
    }

    // 添加头部模板
    public void addHeadItem(int layoutId, Class viewHolder) {
        addItem(TEMPLATE_HEAD, layoutId, viewHolder);
    }

    // 添加底部模板
    public void addBottomItem(int layoutId, Class viewHolder) {
        addItem(TEMPLATE_BOTTOM, layoutId, viewHolder);
    }

    // 设置头部数据
    public void setHeadBean(BaseTemplateBean bean) {
        if (bean != null) {
            bean.setViewType(TEMPLATE_HEAD);
        }
        mHeadBean = bean;
        notifyDataSetChanged();
    }

    public BaseTemplateBean getHeadBean() {
        return mHeadBean;
    }

    // 设置底部数据
    public void setBottomBean(BaseTemplateBean bean) {
        if (bean != null) {
            bean.setViewType(TEMPLATE_BOTTOM);
        }
        mBottomBean = bean;
        notifyDataSetChanged();
    }

    public BaseTemplateBean getBottomBean() {
        return mBottomBean;
    }

    /**
     * 获取主内容数据在数据集中的位置
     *
     * @param adapterPosition
     */
    public int getContentDataPosition(int adapterPosition) {
        if (getHeadBean() != null) {
            adapterPosition--;
        }
        return adapterPosition;
    }

    public void destroy() {
        if (mLayoutIdMap != null) {
            mLayoutIdMap.clear();
            mLayoutIdMap = null;
        }
        if (mViewHolderMap != null) {
            mViewHolderMap.clear();
            mViewHolderMap = null;
        }
        mHeadBean = null;
        mBottomBean = null;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mDatas != null) {
            count = mDatas.size();
        }
        if (mHeadBean != null) {
            count++;
        }
        if (mBottomBean != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeadBean != null) {
            return TEMPLATE_HEAD;
        }
        if (mDatas != null) {
            int itemPosition = position;
            if (mHeadBean != null) {
                itemPosition = position - 1;
            }
            if (itemPosition >= 0 && itemPosition < mDatas.size()) {
                return mDatas.get(itemPosition).viewType;
            }
        }
        return TEMPLATE_BOTTOM;
    }

    @Override
    public BaseViewHolder<BaseTemplateBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutIdMap != null && mViewHolderMap != null) {
            if (mLayoutIdMap.get(viewType) != null) {
                int layoutId = mLayoutIdMap.get(viewType);
                View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
                if (view != null) {
                    try {
                        BaseViewHolder holder = getViewHolder(viewType, view);
                        if (holder != null) {
                            return holder;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_error_vh, parent, false);
        return new BaseViewHolder(view);
    }

    /**
     * 通过反射实例化对应的viewHolder
     *
     * @param viewType
     * @param view
     * @return BaseViewHolder
     */
    protected BaseViewHolder getViewHolder(int viewType, View view) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        if (mViewHolderMap != null) {
            Class viewHolderClass = mViewHolderMap.get(viewType);
            if (viewHolderClass != null) {
                Constructor viewHolderConstructor = viewHolderClass.getConstructor(View.class);
                Object object = viewHolderConstructor.newInstance(view);
                if (object != null) {
                    return (BaseViewHolder) object;
                }
            }
        }
        return null;
    }

    // 绑定
    @Override
    public void onBindViewHolder(BaseViewHolder<BaseTemplateBean> holder, int position) {
        // 绑定头部
        if (mHeadBean != null && position == 0) {
            holder.showData(mHeadBean);
            return;
        }
        // 绑定主内容
        if (mDatas != null) {
            int newPosition = position;
            if (mHeadBean != null) {
                newPosition = position - 1;
            }
            if (newPosition >= 0 && newPosition < mDatas.size()) {
                BaseTemplateBean bean = mDatas.get(newPosition);
                holder.showData(bean);
                return;
            }
        }
        // 绑定底部
        if (mBottomBean != null) {
            holder.showData(mBottomBean);
        }
    }

    // 刷新
    @Override
    public void onBindViewHolder(BaseViewHolder<BaseTemplateBean> holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        // 刷新头部
        if (mHeadBean != null && position == 0) {
            holder.refreshData(mHeadBean);
            return;
        }
        // 刷新主内容
        if (mDatas != null) {
            int newPosition = position;
            if (mHeadBean != null) {
                newPosition = position - 1;
            }
            if (newPosition >= 0 && newPosition < mDatas.size()) {
                BaseTemplateBean bean = mDatas.get(newPosition);
                holder.refreshData(bean);
                return;
            }
        }
        // 刷新底部
        if (mBottomBean != null) {
            holder.refreshData(mBottomBean);
        }
    }
}
