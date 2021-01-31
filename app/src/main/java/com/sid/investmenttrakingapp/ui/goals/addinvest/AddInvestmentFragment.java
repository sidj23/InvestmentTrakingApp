package com.sid.investmenttrakingapp.ui.goals.addinvest;

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
import com.sid.investmenttrakingapp.databinding.FragmentAddInvestmentBinding;

public class AddInvestmentFragment extends Fragment implements AddInvestmentNavigator{

    private static final String ARG_PARAM1 = "arg_param_1";


    private AddInvestmentViewModel addInvestmentViewModel;
    private FragmentAddInvestmentBinding addInvestmentBinding;
    private String fragmentName;

    public static AddInvestmentFragment newInstance(String fragmentName) {
        AddInvestmentFragment fragment = new AddInvestmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        addInvestmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_investment, container, false);
        return addInvestmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addInvestmentViewModel = new ViewModelProvider(this).get(AddInvestmentViewModel.class);
        addInvestmentBinding.setViewModel(addInvestmentViewModel);
        addInvestmentViewModel.setNavigator(this);
    }

    @Override
    public void openDataPickerDialog() {

    }
}