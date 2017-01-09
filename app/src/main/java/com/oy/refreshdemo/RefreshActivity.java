package com.oy.refreshdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oy.adapter.RecyAdapter;
import com.oy.custome.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Lucky on 2017/1/9.
 */
public class RefreshActivity extends AppCompatActivity {
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyAdapter recyAdapter;
    private List<String> mDatas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (RefreshLayout) findViewById(R.id.mRefresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mDatas = new ArrayList<>();
        for(int i = 0;i<20;i++){
            mDatas.add("文本哦"+i);
        }
        recyAdapter = new RecyAdapter(mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyAdapter);
        refreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //网络数据加载
                loadData();
            }
        });
    }
    private void loadData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mDatas.add("刷新");
                recyAdapter.notifyDataSetChanged();
                refreshLayout.refreshComplete();
            }
        }.execute();
    }

}
