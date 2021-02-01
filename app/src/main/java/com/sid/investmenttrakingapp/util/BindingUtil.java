package com.sid.investmenttrakingapp.util;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.sid.investmenttrakingapp.data.InvestmentData;
import com.sid.investmenttrakingapp.ui.goals.TabViewPagerAdapter;
import com.sid.investmenttrakingapp.ui.goals.addinvest.InvestmentDataItemAdapter;

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


    @BindingAdapter({"investItemAdapter"})
    public static void addInvestItemAdapter(RecyclerView recyclerView, List<InvestmentData> dataList) {
        InvestmentDataItemAdapter adapter = (InvestmentDataItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(dataList);
        }
    }
}
