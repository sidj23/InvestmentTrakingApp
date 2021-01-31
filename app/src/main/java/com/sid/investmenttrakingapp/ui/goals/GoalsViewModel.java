package com.sid.investmenttrakingapp.ui.goals;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.sid.investmenttrakingapp.ui.goals.addinvest.AddInvestmentFragment;

import java.util.ArrayList;
import java.util.List;

import static com.sid.investmenttrakingapp.util.AppConstants.ADD_INVESTMENT;
import static com.sid.investmenttrakingapp.util.AppConstants.KIDS_INVESTMENT;
import static com.sid.investmenttrakingapp.util.AppConstants.PERSONAL_INVESTMENT;
import static com.sid.investmenttrakingapp.util.AppConstants.WIFE_INVESTMENT;

public class GoalsViewModel extends ViewModel {

    public final ObservableField<String> tab1Text;
    public final ObservableField<String> tab2Text;
    public final ObservableField<String> tab3Text;
    public final ObservableField<String> tab4Text;

    public final ObservableBoolean isTab1Selected;
    public final ObservableBoolean isTab2Selected;
    public final ObservableBoolean isTab3Selected;
    public final ObservableBoolean isTab4Selected;


    public final ObservableList<Fragment> fragmentObservableList = new ObservableArrayList<>();
    private GoalsNavigator navigator;

    public GoalsViewModel() {
        tab1Text = new ObservableField<>("Add Investment");
        tab2Text = new ObservableField<>("Personal");
        tab3Text = new ObservableField<>("For Wife");
        tab4Text = new ObservableField<>("For Kids");

        isTab1Selected = new ObservableBoolean(false);
        isTab2Selected = new ObservableBoolean(false);
        isTab3Selected = new ObservableBoolean(false);
        isTab4Selected = new ObservableBoolean(false);

        onTabClicked(1);
        setFragmentList();
    }

    private void setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AddInvestmentFragment.newInstance(ADD_INVESTMENT));
        fragmentList.add(AddInvestmentFragment.newInstance(PERSONAL_INVESTMENT));
        fragmentList.add(AddInvestmentFragment.newInstance(WIFE_INVESTMENT));
        fragmentList.add(AddInvestmentFragment.newInstance(KIDS_INVESTMENT));

        fragmentObservableList.clear();
        fragmentObservableList.addAll(fragmentList);
    }

    public void onTabClicked(int selected) {

        if (selected == 1) {
            isTab1Selected.set(true);
        } else if (selected == 2) {
            isTab2Selected.set(true);
        } else if (selected == 3) {
            isTab3Selected.set(true);
        } else if (selected == 4) {
            isTab4Selected.set(true);
        }
        if(navigator!=null)
            navigator.onTabClicked(selected);
        refreshTabSelection(selected);
    }

    private void refreshTabSelection(int selected) {
        if (selected != 1) isTab1Selected.set(false);
        if (selected != 2) isTab2Selected.set(false);
        if (selected != 3) isTab3Selected.set(false);
        if (selected != 4) isTab4Selected.set(false);
    }

    public void setNavigator(GoalsNavigator navigator) {
        this.navigator = navigator;
    }
}