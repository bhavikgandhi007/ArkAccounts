package com.arkaccountslite.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkaccountslite.R;


public class FragmentSummary extends Fragment {


    public FragmentSummary() {

    }

    public static FragmentSummary newInstance() {
        Bundle args = new Bundle();
        FragmentSummary fragment = new FragmentSummary();
        fragment.setArguments(args);
        return fragment;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_summary, null);
        return view;
    }
}
