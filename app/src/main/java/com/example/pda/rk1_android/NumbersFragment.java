package com.example.pda.rk1_android;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    public interface onNumberClicked {
        void numberClickedHandler(Integer number);
    }

    onNumberClicked numberClickedEventListener;
    private NumberAdapter numberAdapter;
    private RecyclerView numberRecyclerView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            numberClickedEventListener = (onNumberClicked) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onNumberClicked interface");
        }
    }


    public NumbersFragment() {
        // Required empty public constructor
    }


    private Collection<NumberedButton> getButtons() {
        List<NumberedButton> nbl = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            NumberedButton nb = new NumberedButton(i);
            nb.setOnClick(numberClickedEventListener);
            nbl.add(nb);
        }
        return nbl;
    }


    private void initRecyclerView(View view) {
        int spanCount = 3;
        numberRecyclerView = view.findViewById(R.id.button_recycle);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        numberRecyclerView.setLayoutManager(mLayoutManager);
        numberAdapter = new NumberAdapter();
        numberRecyclerView.setAdapter(numberAdapter);
    }


    private void initButtons() {
        numberAdapter.setItems(getButtons());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);
        initRecyclerView(view);
        initButtons();
        return view;
    }

}
