package com.sid.investmenttrakingapp.ui.goals.addinvest;

import androidx.databinding.ObservableField;

import com.sid.investmenttrakingapp.data.InvestmentData;

public class InvestmentItemViewModel {
    public final ObservableField<String> dateHint;
    public final ObservableField<String> purchaseDate;
    public final ObservableField<String> unitHint;
    public final ObservableField<String> purchaseUnit;
    public final ObservableField<String> priceHint;
    public final ObservableField<String> purchasePrice;
    public final ObservableField<String> investTypeHint;
    public final ObservableField<String> investType;


    public InvestmentItemViewModel(InvestmentData investmentData) {
        dateHint = new ObservableField<>("Date of Purchase : ");
        purchaseDate = new ObservableField<>(investmentData.getDateOfPurchase());
        unitHint = new ObservableField<>("Number of Units : ");
        purchaseUnit = new ObservableField<>(investmentData.getPurchaseUnits());
        priceHint = new ObservableField<>("Purchase Price : ");
        purchasePrice = new ObservableField<>(investmentData.getPurchasePrice());
        investTypeHint = new ObservableField<>("Investment Type : ");
        investType = new ObservableField<>(investmentData.getInvestmentType());
    }
}
