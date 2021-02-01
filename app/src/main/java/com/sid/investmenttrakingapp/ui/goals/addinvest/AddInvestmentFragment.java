package com.sid.investmenttrakingapp.ui.goals.addinvest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.investmenttrakingapp.R;
import com.sid.investmenttrakingapp.databinding.FragmentAddInvestmentBinding;
import com.sid.investmenttrakingapp.util.PreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.sid.investmenttrakingapp.util.AppConstants.ADD_INVESTMENT;

public class AddInvestmentFragment extends Fragment implements AddInvestmentNavigator {

    private static final String ARG_PARAM1 = "arg_param_1";
    public static AddInvestmentFragmentListener listener;
    private AddInvestmentViewModel addInvestmentViewModel;
    private FragmentAddInvestmentBinding addInvestmentBinding;
    private String fragmentName;
    private ListPopupWindow listPopupWindow;
    private InvestmentDataItemAdapter dataItemAdapter;

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
        addInvestmentViewModel.setPreferenceHelper(new PreferenceHelper(getContext()));

        if (fragmentName.equals(ADD_INVESTMENT)) {
            addInvestmentViewModel.isForAddInvestment.set(true);
        } else {
            addInvestmentViewModel.isForAddInvestment.set(false);
            addInvestmentViewModel.setUpDataSource(fragmentName);
        }

        setUpView();

    }

    private void setUpView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        dataItemAdapter = new InvestmentDataItemAdapter();

        addInvestmentBinding.faiInvestDataRv.setLayoutManager(layoutManager);
        addInvestmentBinding.faiInvestDataRv.setAdapter(dataItemAdapter);
    }

    @Override
    public void openDataPickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);

                        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
                        String strDate = format.format(calendar.getTime());

                        addInvestmentViewModel.setDate(strDate);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void showListPopUp() {
        List<String> optionList = new ArrayList<>();
        optionList.add("Personal");
        optionList.add("For Wife");
        optionList.add("For Kids");

        listPopupWindow = new ListPopupWindow(getContext());
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.item_popup_list, R.id.tv_element, optionList);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setAnchorView(addInvestmentBinding.faiGroupEt);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addInvestmentViewModel.setGroupInvestment(optionList.get(i));
                if (listPopupWindow != null)
                    listPopupWindow.dismiss();
            }
        });

        listPopupWindow.show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDataInViewPagerTab(String tabName) {
        if (listener != null)
            listener.refreshTabData(tabName);
    }

    public void refreshInvestmentData() {
        addInvestmentViewModel.setUpDataSource(fragmentName);
    }


    public interface AddInvestmentFragmentListener {
        void refreshTabData(String tabName);
    }

}