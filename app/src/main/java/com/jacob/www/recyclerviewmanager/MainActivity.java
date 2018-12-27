package com.jacob.www.recyclerviewmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jacob.www.recyclerviewmanager.demo.DemoAdapter;
import com.jacob.www.recyclerviewmanager.demo.DemoBean1;
import com.jacob.www.recyclerviewmanager.demo.DemoBean2;
import com.jacob.www.recyclerviewmanager.demo.DemoBean3;
import com.jacob.www.recyclerviewmanager.bean.BaseTemplateBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView mTvAddHead;
    public TextView mTvAddBottom;
    public RecyclerView mRecyclerView;
    public DemoAdapter mDemoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvAddHead = (TextView) findViewById(R.id.tv_add_head);
        mTvAddBottom = (TextView) findViewById(R.id.tv_add_bottom);
        mTvAddHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了添加头部",Toast.LENGTH_SHORT).show();
                DemoBean1 demoBean1 = new DemoBean1();
                demoBean1.tvStr = "我是头部";
                mDemoAdapter.setHeadBean(demoBean1);
            }
        });
        mTvAddBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了添加底部",Toast.LENGTH_SHORT).show();
                DemoBean3 demoBean3 = new DemoBean3();
                demoBean3.tvStr = "我是底部";
                mDemoAdapter.setBottomBean(demoBean3);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mDemoAdapter = new DemoAdapter();
        mRecyclerView.setAdapter(mDemoAdapter);
        List<BaseTemplateBean> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoBean2 demoBean2 = new DemoBean2();
            demoBean2.tvStr =i+"个数据";
            datas.add(demoBean2);
        }
        mDemoAdapter.setDatas(datas);
    }
}
