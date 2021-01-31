package com.sid.investmenttrakingapp.ui.goals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sid.investmenttrakingapp.R;
import com.sid.investmenttrakingapp.databinding.FragmentGoalsBinding;

public class GoalsFragment extends Fragment implements GoalsNavigator {

    private GoalsViewModel goalsViewModel;
    private FragmentGoalsBinding goalsBinding;
    private TabViewPagerAdapter tabViewPagerAdapter;


    public static GoalsFragment newInstance() {
        return new GoalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        goalsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_goals, container, false);
        return goalsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        goalsViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        goalsViewModel.setNavigator(this);
        goalsBinding.setViewModel(goalsViewModel);

        setUpView();

    }

    private void setUpView() {
        tabViewPagerAdapter = new TabViewPagerAdapter(getFragmentManager(), 0);
        goalsBinding.fgGoalsVp.setAdapter(tabViewPagerAdapter);
        goalsBinding.fgGoalsVp.setOffscreenPageLimit(4);
    }

    @Override
    public void onTabClicked(int selectedTab) {
        if (selectedTab > 0)
            goalsBinding.fgGoalsVp.setCurrentItem(selectedTab - 1, true);
    }
}