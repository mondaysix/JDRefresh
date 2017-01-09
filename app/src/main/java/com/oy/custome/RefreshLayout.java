package com.oy.custome;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Lucky on 2017/1/9.
 */
public class RefreshLayout extends PtrFrameLayout {
    RefreshHeader refreshHeader;
    public RefreshLayout(Context context) {
        this(context,null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
            refreshHeader = new RefreshHeader(getContext());
            addView(refreshHeader);
            addPtrUIHandler(refreshHeader);
    }
}
