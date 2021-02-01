package com.sid.investmenttrakingapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sid.investmenttrakingapp.data.InvestmentData;

import java.lang.reflect.Type;
import java.util.List;

import static com.sid.investmenttrakingapp.util.AppConstants.PREF_NAME;

public class PreferenceHelper {

    private static final String PREF_KEY_PERSONAL_INVESTMENT_DATA = "PREF_KEY_PERSONAL_INVESTMENT_DATA";
    private static final String PREF_KEY_WIFE_INVESTMENT_DATA = "PREF_KEY_WIFE_INVESTMENT_DATA";
    private static final String PREF_KEY_KIDS_INVESTMENT_DATA = "PREF_KEY_KIDS_INVESTMENT_DATA";
    private final SharedPreferences mPrefs;

    public PreferenceHelper(Context context) {
        this.mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<InvestmentData> getPersonalInvestmentData() {
        Type type = new TypeToken<List<InvestmentData>>() {
        }.getType();
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_PERSONAL_INVESTMENT_DATA, ""), type);
    }

    public void setPersonalInvestmentData(List<InvestmentData> dataList) {
        mPrefs.edit().putString(PREF_KEY_PERSONAL_INVESTMENT_DATA, new Gson().toJson(dataList)).apply();
    }

    public List<InvestmentData> getWifeInvestmentData() {
        Type type = new TypeToken<List<InvestmentData>>() {
        }.getType();
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_WIFE_INVESTMENT_DATA, ""), type);
    }

    public void setWifeInvestmentData(List<InvestmentData> dataList) {
        mPrefs.edit().putString(PREF_KEY_WIFE_INVESTMENT_DATA, new Gson().toJson(dataList)).apply();
    }

    public List<InvestmentData> getKidsInvestmentData() {
        Type type = new TypeToken<List<InvestmentData>>() {
        }.getType();
        return new Gson().fromJson(mPrefs.getString(PREF_KEY_KIDS_INVESTMENT_DATA, ""), type);
    }

    public void setKidsInvestmentData(List<InvestmentData> dataList) {
        mPrefs.edit().putString(PREF_KEY_KIDS_INVESTMENT_DATA, new Gson().toJson(dataList)).apply();
    }
}
