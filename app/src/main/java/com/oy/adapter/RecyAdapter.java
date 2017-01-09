package com.oy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oy.refreshdemo.R;

import java.util.List;

/**
 * Created by Lucky on 2017/1/9.
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MviewHolder> {
    private List<String> mDatas;
    private TextView tv;
    public RecyAdapter(List<String> mDatas){
        this.mDatas = mDatas;
    }

    @Override
    public MviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item,parent,false);

        MviewHolder mViewHolder = new MviewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MviewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("msg", "getItemCount: "+mDatas.size());
        return mDatas.size();
    }
    public class MviewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MviewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
