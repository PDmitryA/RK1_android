package com.example.pda.rk1_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NumbersFragment.onNumberClicked {

    NumbersFragment numbersFragment = null;
    SumFragment sumFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        numbersFragment = ((NumbersFragment) getSupportFragmentManager().findFragmentById(R.id.numbers_fragment));
        sumFragment = ((SumFragment) getSupportFragmentManager().findFragmentById(R.id.sum_fragment));
    }

    @Override
    public void numberClickedHandler(Integer number) {
        sumFragment.addSum(number);
    }
}
