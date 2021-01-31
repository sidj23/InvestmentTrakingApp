package com.sid.investmenttrakingapp.util;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.sid.investmenttrakingapp.ui.goals.TabViewPagerAdapter;

import java.util.List;

public class BindingUtil {

    @BindingAdapter({"tabViewPager"})
    public static void addViewPager(ViewPager viewPager, List<Fragment> fragmentList) {
        TabViewPagerAdapter adapter = (TabViewPagerAdapter) viewPager.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(fragmentList);
        }
    }

}
