package com.jacob.www.recyclerviewmanager.demo;

import com.jacob.www.recyclerviewmanager.R;
import com.jacob.www.recyclerviewmanager.adapter.CustomRVAdapter;

/**
 * Description: ${DemoAdapter}
 * Created by JacobYu，2018/12/20
 */
public class DemoAdapter extends CustomRVAdapter {

    public static final int DEMO_VIEW_TYPE_2 = 0x0002;

    public DemoAdapter() {
        super();

        addHeadItem(R.layout.item_view_type_1,DemoViewHolder1.class);
        addBottomItem(R.layout.item_view_type_3,DemoViewHolder3.class);
        addItem(DEMO_VIEW_TYPE_2,R.layout.item_view_type_2,DemoViewHolder2.class);
    }
}
