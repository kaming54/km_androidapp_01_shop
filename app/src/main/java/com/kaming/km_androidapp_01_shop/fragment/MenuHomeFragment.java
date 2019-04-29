package com.kaming.km_androidapp_01_shop.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MenuHomeFragment extends BaseFragment{

    private TextView textView;
    private static final String TAG = MenuHomeFragment.class.getSimpleName();

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        Log.e(TAG, "主页面的Fragment的UI被初始化了 ");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("首頁");
        Log.e(TAG, "主页面的Fragment的数据被初始化了");
    }
}
