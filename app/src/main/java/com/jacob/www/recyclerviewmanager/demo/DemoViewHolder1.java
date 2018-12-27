package com.jacob.www.recyclerviewmanager.demo;

import android.view.View;
import android.widget.TextView;

import com.jacob.www.recyclerviewmanager.R;
import com.jacob.www.recyclerviewmanager.viewholder.BaseViewHolder;

/**
 * Description: ${DemoViewHolder}
 * Created by JacobYu，2018/12/20
 */
public class DemoViewHolder1 extends BaseViewHolder<DemoBean1> {

    public final TextView mTv;

    public DemoViewHolder1(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv_view_type_1);
    }

    @Override
    public void showData(DemoBean1 data) {
        mTv.setText(data.tvStr);
    }
}
