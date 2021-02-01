package com.sid.investmenttrakingapp.ui.goals.addinvest;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.investmenttrakingapp.base.BaseViewHolder;
import com.sid.investmenttrakingapp.data.InvestmentData;
import com.sid.investmenttrakingapp.databinding.ItemInvestmentBinding;

import java.util.ArrayList;
import java.util.List;

public class InvestmentDataItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<InvestmentData> investmentDataList = new ArrayList<>();


    public void addItems(List<InvestmentData> investmentData) {
        investmentDataList.addAll(investmentData);
        notifyDataSetChanged();
    }

    public void clearItems() {
        investmentDataList.clear();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInvestmentBinding binding = ItemInvestmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InvestDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return investmentDataList.size();
    }

    private class InvestDataViewHolder extends BaseViewHolder {

        private ItemInvestmentBinding investmentBinding;

        public InvestDataViewHolder(ItemInvestmentBinding binding) {
            super(binding.getRoot());
            investmentBinding = binding;
        }

        @Override
        public void onBind(int position) {
            InvestmentData investmentData = investmentDataList.get(position);
            if (investmentData != null) {
                InvestmentItemViewModel viewModel = new InvestmentItemViewModel(investmentData);
                investmentBinding.setViewModel(viewModel);
                investmentBinding.executePendingBindings();
            }
        }
    }
}
