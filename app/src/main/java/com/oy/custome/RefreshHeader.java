package com.oy.custome;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oy.refreshdemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Lucky on 2017/1/9.
 */
public class RefreshHeader extends FrameLayout implements PtrUIHandler {
    private ImageView iv_man,iv_goods;
    private TextView tv_remain;
    private int mState;
    /**
     * 重置
     * 准备刷新
     * 开始刷新
     * 结束刷新
     */
    public static final int STATE_RESET = -1;
    public static final int STATE_PREPARE = 0;
    public static final int STATE_BEGIN = 1;
    public static final int STATE_FINISH = 2;
    public AnimationDrawable animDrawable;
    public static final int MARGIN_RIGHT = 100;
    public RefreshHeader(Context context) {
        this(context,null);
    }

    public RefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
       View view =  LayoutInflater.from(getContext()).inflate(R.layout.refresh_header_view,this,false);
        iv_man = (ImageView) view.findViewById(R.id.iv_man);
        iv_goods = (ImageView) view.findViewById(R.id.iv_goods);
        tv_remain = (TextView) view.findViewById(R.id.tv_remain);

        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
        //隐藏商品
        iv_goods.setVisibility(View.GONE);
        iv_man.setBackgroundResource(R.drawable.running_anim);
        animDrawable  = (AnimationDrawable) iv_man.getBackground();
        if (!animDrawable.isRunning()){
            animDrawable.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mState = STATE_FINISH;
        iv_goods.setVisibility(View.VISIBLE);
        if (animDrawable.isRunning()){
            animDrawable.stop();
        }
        iv_man.setBackgroundResource(R.drawable.man1);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //提示文字变化
        switch (mState){
            case STATE_PREPARE:
                //logo设置
                iv_man.setAlpha(ptrIndicator.getCurrentPercent());
                iv_goods.setAlpha(ptrIndicator.getCurrentPercent());
                FrameLayout.LayoutParams mIvManLayoutParams = (LayoutParams) iv_man.getLayoutParams();
                if (ptrIndicator.getCurrentPercent() <= 1) {
                    iv_man.setScaleX(ptrIndicator.getCurrentPercent());
                    iv_man.setScaleY(ptrIndicator.getCurrentPercent());
                    iv_goods.setScaleX(ptrIndicator.getCurrentPercent());
                    iv_goods.setScaleY(ptrIndicator.getCurrentPercent());
                    int marginRight = (int) (MARGIN_RIGHT - MARGIN_RIGHT * ptrIndicator.getCurrentPercent());
                    mIvManLayoutParams.setMargins(0, 0, marginRight, 0);
                    iv_man.setLayoutParams(mIvManLayoutParams);
                }
                if (ptrIndicator.getCurrentPercent() < 1.2) {
                    tv_remain.setText("下拉刷新...");
                } else {
                    tv_remain.setText("松开刷新...");
                }
                break;
            case STATE_BEGIN:
                tv_remain.setText("加载中...");
                break;
            case STATE_FINISH:
                tv_remain.setText("加载完成...");
                break;
        }
    }
}
