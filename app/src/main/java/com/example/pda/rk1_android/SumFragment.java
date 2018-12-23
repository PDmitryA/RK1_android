package com.example.pda.rk1_android;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SumFragment extends Fragment {

    private Integer sum = 0;
    private TextView sumView;


    public SumFragment() {
        // Required empty public constructor
    }


    public void addSum(int add) {
        sum += add;
        updateSum();
    }


    private void updateSum() {
        sumView.setText(String.valueOf(sum));
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sum, container, false);
        sumView = view.findViewById(R.id.sum);
        updateSum();
        return view;
    }

}
