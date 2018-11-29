package com.example.pda.rk1_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int MAX_NUMBER = 100;
    int COL_NUMBER = 4;
    int INIT_SUM = 0;

    TableLayout tableNumbers;
    TextView sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        tableNumbers = findViewById(R.id.numbers_container);
        sum = findViewById(R.id.sum);

        initDefaultSum();

        TableRow tr = null;
        int i = 0;
        while (i < MAX_NUMBER) {
            if (i % COL_NUMBER == 0) {
                tr = new TableRow(this);
                tableNumbers.addView(tr);
            }
            Button btn = new Button(this);
            btn.setText(String.valueOf(i));
            btn.setId(i);
            btn.setOnClickListener(clickListener);
            btn.setBackgroundResource(R.color.colorPrimary);
            tr.addView(btn);
            i++;
        }
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        String CLICKED_TAG = "CLICKED";

        @Override
        public void onClick(final View view) {
            Button clicked = (Button) view;
            Integer id = view.getId();

            Integer currentSum = Integer.parseInt((String) sum.getText());

            String prev_clicked = (String)clicked.getTag();
            if (prev_clicked != null) {
                clicked.setTag(null);
                clicked.setBackgroundResource(R.color.colorPrimary);
                currentSum -= id;
                sum.setText(String.valueOf(currentSum));
                return;
            }

            clicked.setTag(CLICKED_TAG);
            clicked.setBackgroundResource(R.color.colorGreen);

            currentSum += id;
            sum.setText(String.valueOf(currentSum));
        }
    };

    void initDefaultSum() {
        sum.setText(String.valueOf(INIT_SUM));
    }
}
