package com.sid.investmenttrakingapp.data;

public class InvestmentData {

    private String investmentType;
    private String investFor;
    private String dateOfPurchase;
    private String purchaseUnits;
    private String purchasePrice;

    public InvestmentData(String investFor, String investmentType, String dateOfPurchase, String purchaseUnits, String purchasePrice) {
        this.investmentType = investmentType;
        this.investFor = investFor;
        this.dateOfPurchase = dateOfPurchase;
        this.purchaseUnits = purchaseUnits;
        this.purchasePrice = purchasePrice;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public String getInvestFor() {
        return investFor;
    }

    public void setInvestFor(String investFor) {
        this.investFor = investFor;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getPurchaseUnits() {
        return purchaseUnits;
    }

    public void setPurchaseUnits(String purchaseUnits) {
        this.purchaseUnits = purchaseUnits;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
