package com.sid.investmenttrakingapp.ui.goals.addinvest;

public interface AddInvestmentNavigator {

    void openDataPickerDialog();

    void showListPopUp();

    void showErrorMessage(String message);

    void setDataInViewPagerTab(String tabName);
}
