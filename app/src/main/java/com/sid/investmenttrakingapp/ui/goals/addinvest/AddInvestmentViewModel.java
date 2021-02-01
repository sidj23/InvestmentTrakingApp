package com.sid.investmenttrakingapp.ui.goals.addinvest;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.sid.investmenttrakingapp.data.InvestmentData;
import com.sid.investmenttrakingapp.util.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import static com.sid.investmenttrakingapp.util.AppConstants.KIDS_INVESTMENT;
import static com.sid.investmenttrakingapp.util.AppConstants.PERSONAL_INVESTMENT;
import static com.sid.investmenttrakingapp.util.AppConstants.WIFE_INVESTMENT;

public class AddInvestmentViewModel extends ViewModel {

    public final ObservableField<String> selectionHint;
    public final ObservableField<String> investmentGroup;
    public final ObservableField<String> radioButtonHint;
    public final ObservableField<String> fixedHint;
    public final ObservableField<String> variableHint;
    public final ObservableField<String> dateHint;
    public final ObservableField<String> purchaseDate;
    public final ObservableField<String> unitHint;
    public final ObservableField<String> purchaseUnit;
    public final ObservableField<String> priceHint;
    public final ObservableField<String> purchasePrice;
    public final ObservableField<String> addMoreHint;
    public final ObservableField<String> noDataText;

    public final ObservableBoolean isSwitchChecked;
    public final ObservableBoolean isForAddInvestment;
    public final ObservableBoolean isListAvailble;

    public final ObservableList<InvestmentData> dataObservableList = new ObservableArrayList<>();

    private AddInvestmentNavigator navigator;
    private PreferenceHelper preferenceHelper;


    public AddInvestmentViewModel() {
        selectionHint = new ObservableField<>("Who is this Investment for ?");
        investmentGroup = new ObservableField<>("");
        radioButtonHint = new ObservableField<>("Is the equity one time or recurring");
        fixedHint = new ObservableField<>("Fixed");
        variableHint = new ObservableField<>("Variable");
        dateHint = new ObservableField<>("Date of Purchase");
        purchaseDate = new ObservableField<>("");
        unitHint = new ObservableField<>("Number of Units");
        purchaseUnit = new ObservableField<>("");
        priceHint = new ObservableField<>("Purchase Price");
        purchasePrice = new ObservableField<>("");
        addMoreHint = new ObservableField<>("Add More");
        isSwitchChecked = new ObservableBoolean(false);
        isForAddInvestment = new ObservableBoolean(false);
        noDataText = new ObservableField<>("No Data Available");
        isListAvailble = new ObservableBoolean(false);
    }

    public void onPurchaseDateClick() {
        if (navigator != null) {
            navigator.openDataPickerDialog();
        }
    }

    public void onInvestmentGroupClicked() {
        if (navigator != null)
            navigator.showListPopUp();
    }

    public void setNavigator(AddInvestmentNavigator navigator) {
        this.navigator = navigator;
    }

    public void setDate(String strDate) {
        purchaseDate.set(strDate);
    }

    public void setGroupInvestment(String investmentGroup) {
        this.investmentGroup.set(investmentGroup);
    }

    public void onAddMoreClicked() {
        if (investmentGroup == null || investmentGroup.get().isEmpty() ||
                purchaseDate == null || purchaseDate.get().isEmpty() ||
                purchaseUnit == null || purchaseUnit.get().isEmpty() ||
                purchasePrice == null || purchasePrice.get().isEmpty()) {
            navigator.showErrorMessage("Please enter All the Fields");
        } else {
            addToSharedPreference();
            resetFields();
            navigator.showErrorMessage("Added SuccessFully");
        }
    }

    private void resetFields() {
        investmentGroup.set("");
        purchaseDate.set("");
        purchasePrice.set("");
        purchaseUnit.set("");
        isSwitchChecked.set(false);
    }

    private void addToSharedPreference() {
        InvestmentData data = new InvestmentData(investmentGroup.get(), isSwitchChecked.get() ? "Recurring" : "Fixed", purchaseDate.get(), purchaseUnit.get(), purchasePrice.get());
        if (investmentGroup.get().equalsIgnoreCase("Personal")) {
            if (preferenceHelper != null) {
                if (preferenceHelper.getPersonalInvestmentData() != null) {
                    List<InvestmentData> investmentDataList = preferenceHelper.getPersonalInvestmentData();
                    investmentDataList.add(0, data);
                    preferenceHelper.setPersonalInvestmentData(investmentDataList);
                } else {
                    List<InvestmentData> investmentDataList = new ArrayList<>();
                    investmentDataList.add(0, data);
                    preferenceHelper.setPersonalInvestmentData(investmentDataList);
                }
            } else {
                navigator.showErrorMessage("OOPs!! Some Error Occured");
            }
        } else if (investmentGroup.get().equalsIgnoreCase("For Wife")) {
            if (preferenceHelper != null) {
                if (preferenceHelper.getWifeInvestmentData() != null) {
                    List<InvestmentData> investmentDataList = preferenceHelper.getWifeInvestmentData();
                    investmentDataList.add(0, data);
                    preferenceHelper.setWifeInvestmentData(investmentDataList);
                } else {
                    List<InvestmentData> investmentDataList = new ArrayList<>();
                    investmentDataList.add(0, data);
                    preferenceHelper.setWifeInvestmentData(investmentDataList);
                }
            } else {
                navigator.showErrorMessage("OOPs!! Some Error Occured");
            }
        } else if (investmentGroup.get().equalsIgnoreCase("For Kids")) {
            if (preferenceHelper != null) {
                if (preferenceHelper.getKidsInvestmentData() != null) {
                    List<InvestmentData> investmentDataList = preferenceHelper.getKidsInvestmentData();
                    investmentDataList.add(0, data);
                    preferenceHelper.setKidsInvestmentData(investmentDataList);
                } else {
                    List<InvestmentData> investmentDataList = new ArrayList<>();
                    investmentDataList.add(0, data);
                    preferenceHelper.setKidsInvestmentData(investmentDataList);
                }
            } else {
                navigator.showErrorMessage("OOPs!! Some Error Occured");
            }
        }
        navigator.setDataInViewPagerTab(investmentGroup.get());
    }


    public void setPreferenceHelper(PreferenceHelper preferenceHelper) {
        this.preferenceHelper = preferenceHelper;
    }

    public void setUpDataSource(String fragmentName) {
        if (fragmentName.equals(PERSONAL_INVESTMENT)) {
            if (preferenceHelper != null && preferenceHelper.getPersonalInvestmentData() != null && preferenceHelper.getPersonalInvestmentData().size() > 0) {
                setObservableList(preferenceHelper.getPersonalInvestmentData());
            } else {
                isListAvailble.set(false);
            }
        } else if (fragmentName.equals(WIFE_INVESTMENT)) {
            if (preferenceHelper != null && preferenceHelper.getWifeInvestmentData() != null && preferenceHelper.getWifeInvestmentData().size() > 0) {
                setObservableList(preferenceHelper.getWifeInvestmentData());
            } else {
                isListAvailble.set(false);
            }
        } else if (fragmentName.equals(KIDS_INVESTMENT)) {
            if (preferenceHelper != null && preferenceHelper.getKidsInvestmentData() != null && preferenceHelper.getKidsInvestmentData().size() > 0) {
                setObservableList(preferenceHelper.getKidsInvestmentData());
            } else {
                isListAvailble.set(false);
            }
        }
    }

    private void setObservableList(List<InvestmentData> investmentDataList) {
        dataObservableList.clear();
        dataObservableList.addAll(investmentDataList);

        isListAvailble.set(true);
    }
}