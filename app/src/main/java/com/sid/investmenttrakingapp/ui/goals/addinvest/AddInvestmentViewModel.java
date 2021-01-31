package com.sid.investmenttrakingapp.ui.goals.addinvest;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

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
    private AddInvestmentNavigator navigator;


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
    }

    public void onPurchaseDateClick() {
        if (navigator != null) {
            navigator.openDataPickerDialog();
        }
    }

    public void setNavigator(AddInvestmentNavigator navigator) {
        this.navigator = navigator;
    }
}