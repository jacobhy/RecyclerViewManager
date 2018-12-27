package com.jacob.www.recyclerviewmanager.demo;

import android.view.View;
import android.widget.TextView;

import com.jacob.www.recyclerviewmanager.R;
import com.jacob.www.recyclerviewmanager.viewholder.BaseViewHolder;

/**
 * Description: ${DemoViewHolder}
 * Created by JacobYu，2018/12/20
 */
public class DemoViewHolder3 extends BaseViewHolder<DemoBean3> {

    public final TextView mTv;

    public DemoViewHolder3(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv_view_type_3);
    }

    @Override
    public void showData(DemoBean3 data) {
        mTv.setText(data.tvStr);
    }
}
