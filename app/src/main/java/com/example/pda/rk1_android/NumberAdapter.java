package com.example.pda.rk1_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NumberedButton> numberedButtonsList = new ArrayList<>();

    interface ClickableNumberedButton {
        void bind(NumberedButton nb);
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements ClickableNumberedButton {
        private Button numberedButton;

        NumberViewHolder(View itemView) {
            super(itemView);
            numberedButton = itemView.findViewById(R.id.numbered_button);
        }

        public void bind(NumberedButton nb) {
            initButton(numberedButton, nb);
        }
    }

    class ClickedNumberViewHolder extends RecyclerView.ViewHolder implements ClickableNumberedButton {
        private Button clickedNumberedButton;

        ClickedNumberViewHolder(View itemView) {
            super(itemView);
            clickedNumberedButton = itemView.findViewById(R.id.clicked_numbered_button);
        }

        public void bind(NumberedButton nb) {
            initButton(clickedNumberedButton, nb);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clicked = (Button) v;
            Integer number = (Integer) clicked.getTag();

            ListIterator iter = numberedButtonsList.listIterator();
            while (iter.hasNext()) {
                NumberedButton nb = (NumberedButton) iter.next();
                if (nb.getNumber().equals(number)) {
                    nb.getOnClick().numberClickedHandler(nb.getClicked() ? -number : number);
                    nb.setClicked(!nb.getClicked());

                    notifyItemChanged(iter.previousIndex());
                    return;
                }
            }
        }
    };

    private void initButton(Button button, NumberedButton numberedButton) {
        button.setText(String.valueOf(numberedButton.getNumber()));
        button.setTag(numberedButton.getNumber());
        button.setOnClickListener(onClickListener);
    }

    public void setItems(Collection<NumberedButton> numberedButtons) {
        numberedButtonsList.addAll(numberedButtons);
        notifyDataSetChanged();
    }

    public void clearItems() {
        numberedButtonsList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (numberedButtonsList.get(position).getClicked()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return numberedButtonsList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view0 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.numbered_button, parent, false);
                return new NumberViewHolder(view0);
            default:
                View view1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.clicked_numbered_button, parent, false);
                return new ClickedNumberViewHolder(view1);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ClickableNumberedButton)holder).bind(numberedButtonsList.get(position));
    }
}
