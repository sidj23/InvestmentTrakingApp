package com.sid.investmenttrakingapp.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sid.investmenttrakingapp.R;

public class CommonFragment extends Fragment {

    private static final String ARG_PARAM1 = "arg_param_1";


    private String fragmentName;
    private View mView;

    public CommonFragment() {
    }

    public static CommonFragment newInstance(String fragmentName) {
        CommonFragment fragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_common, container, false);

        TextView headingTv = mView.findViewById(R.id.fc_heading_tv);

        if (fragmentName.equalsIgnoreCase("dashboard")) {
            headingTv.setText("Welcome to Dashboard");
        } else {
            headingTv.setText("Welcome to Profile");
        }

        return mView;
    }
}