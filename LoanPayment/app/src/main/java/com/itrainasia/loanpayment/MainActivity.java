package com.itrainasia.loanpayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    Button calc;

    EditText total;
    EditText downpayment;
    EditText year;
    double interest;

    TextView display;
    TextView display_down;
    TextView interest_display;

    SeekBar seekbar;

    double total_loan,down_percentage, down_payment_amount, annual_interest,discount, loan_year, month_pay, amount_to_pay,interest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = (Button) findViewById(R.id.calculate);
        total = (EditText) findViewById(R.id.totalloan);
        downpayment = (EditText) findViewById(R.id.percentage);
        display = (TextView) findViewById(R.id.display);
        year = (EditText) findViewById(R.id.year);
        display_down = (TextView) findViewById(R.id.downpayment_amount);
        interest_display = (TextView) findViewById(R.id.seekbar_display);

        seekbar = (SeekBar) findViewById(R.id.seekbar);

        downpayment.setOnFocusChangeListener(this);
        seekbar.setOnSeekBarChangeListener(this);
        calc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        total_loan = Double.parseDouble(total.getText().toString());
        down_percentage = Double.parseDouble(downpayment.getText().toString());
        down_payment_amount = total_loan /down_percentage;

        loan_year = Double.parseDouble(year.getText().toString());
        interest2 = interest;

        annual_interest = (interest2/100)/12;

        discount = (pow((1+ annual_interest),(loan_year *12))-1) / (annual_interest * (pow((1+ annual_interest),(loan_year *12))));

        amount_to_pay = total_loan - down_payment_amount;

        month_pay = amount_to_pay /discount;

        String message = String.format("Amount to pay is %.2f", month_pay);

        display.setText(message);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!v.hasFocus()) {
            total_loan = Double.parseDouble(total.getText().toString());
            down_percentage = Double.parseDouble(downpayment.getText().toString());
            down_payment_amount = total_loan / down_percentage;
            display_down.setText("RM "+down_payment_amount);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        double value = (double) progress/100*20;
        Log.d("debug","value is "+value);
        interest = value;
        interest_display.setText(String.format("%.1f",value));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
