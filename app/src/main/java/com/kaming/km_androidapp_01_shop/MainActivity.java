package com.kaming.km_androidapp_01_shop;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.kaming.km_androidapp_01_shop.fragment.BaseFragment;
import com.kaming.km_androidapp_01_shop.fragment.MenuCartFragment;
import com.kaming.km_androidapp_01_shop.fragment.MenuHomeFragment;
import com.kaming.km_androidapp_01_shop.fragment.MenuMeFragment;
import com.kaming.km_androidapp_01_shop.fragment.MenuShopFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    FrameLayout frameLayout;
    RadioGroup rgMenu;

    //裝fragment實例的集合
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    //暫存上次顯示的fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.menu_frame_layout);
        rgMenu = (RadioGroup) findViewById(R.id.menu_group);
        initFragment();
        initListener();
        setMenuIconSize();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MenuHomeFragment());
        fragments.add(new MenuShopFragment());
        fragments.add(new MenuCartFragment());
        fragments.add(new MenuMeFragment());
    }

    private void initListener() {
        rgMenu.check(R.id.menu_home_rb);
        rgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.menu_home_rb:
                        position = 0;
                        break;
                    case R.id.menu_shop_rb:
                        position = 1;
                        break;
                    case R.id.menu_cart_rb:
                        position = 2;
                        break;
                    case R.id.menu_me_rb:
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment,baseFragment);
            }
        });
    }

    /**
     *  根據position得到對象的fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(fragments != null&&fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 轉換fragment
     * @param fragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fragment,BaseFragment nextFragment){
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判斷nextFragment是否增加了
                if(!nextFragment.isAdded()){
                    //隱藏當前的fragment
                    if(fragment!=null){
                        transaction.hide(fragment);
                    }
                    //增加fragment
                    transaction.add(R.id.menu_frame_layout,nextFragment).commit();
                }else {
                    //隱藏當前fragment
                    if(fragment != null){
                        transaction.hide(fragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void setMenuIconSize(){
        RadioButton rgMenuHome = (RadioButton) findViewById(R.id.menu_home_rb);
        RadioButton rgMenuShop = (RadioButton) findViewById(R.id.menu_shop_rb);
        RadioButton rgMenuCart = (RadioButton) findViewById(R.id.menu_cart_rb);
        RadioButton rgMenuMe = (RadioButton) findViewById(R.id.menu_me_rb);
        Drawable imgMenuHome = getResources().getDrawable(R.drawable.menu_home_selector);
        Drawable imgMenuShop = getResources().getDrawable(R.drawable.menu_shop_selector);
        Drawable imgMenuCart = getResources().getDrawable(R.drawable.menu_cart_selector);
        Drawable imgMenuMe = getResources().getDrawable(R.drawable.menu_me_selector);
        imgMenuHome.setBounds(0, 0, 120, 120);
        imgMenuShop.setBounds(0, 0, 120, 120);
        imgMenuCart.setBounds(0, 0, 120, 120);
        imgMenuMe.setBounds(0, 0, 120, 120);
        rgMenuHome.setCompoundDrawables(null, imgMenuHome, null, null);
        rgMenuShop.setCompoundDrawables(null, imgMenuShop, null, null);
        rgMenuCart.setCompoundDrawables(null, imgMenuCart, null, null);
        rgMenuMe.setCompoundDrawables(null, imgMenuMe, null, null);
    }
}
