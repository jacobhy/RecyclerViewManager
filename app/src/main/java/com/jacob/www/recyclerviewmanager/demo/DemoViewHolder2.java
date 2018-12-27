package com.jacob.www.recyclerviewmanager.demo;

import android.view.View;
import android.widget.TextView;

import com.jacob.www.recyclerviewmanager.R;
import com.jacob.www.recyclerviewmanager.viewholder.BaseViewHolder;

/**
 * Description: ${DemoViewHolder}
 * Created by JacobYu，2018/12/20
 */
public class DemoViewHolder2 extends BaseViewHolder<DemoBean2> {

    public final TextView mTv;

    public DemoViewHolder2(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv_view_type_2);
    }

    @Override
    public void showData(DemoBean2 data) {
        mTv.setText(data.tvStr);
    }
}
