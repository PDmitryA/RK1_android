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

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<NumberedButton> numberedButtonsList = new ArrayList<>();

    class NumberViewHolder extends RecyclerView.ViewHolder {
        private Button numberedButton;

        NumberViewHolder(View itemView) {
            super(itemView);
            numberedButton = itemView.findViewById(R.id.numbered_button);
        }

        void bind(NumberedButton nb) {
            updateButton(numberedButton, nb);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clicked = (Button) v;
            Integer number = (Integer) clicked.getTag();

            for (NumberedButton nb: numberedButtonsList) {
                if (nb.getNumber().equals(number)) {
                    nb.getOnClick().numberClickedHandler(nb.getClicked() ? -number : number);
                    nb.setClicked(!nb.getClicked());
                    updateButton(clicked, nb);
                }
            }
        }
    };

    private void updateButton(Button button, NumberedButton numberedButton) {
        button.setText(String.valueOf(numberedButton.getNumber()));
        int background = numberedButton.isClicked ? R.color.colorPrimary : R.color.colorGreen;
        button.setTag(numberedButton.getNumber());
        button.setBackgroundResource(background);
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
    public int getItemCount() {
        return numberedButtonsList.size();
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numbered_button, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(numberedButtonsList.get(position));
    }
}
