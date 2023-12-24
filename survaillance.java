package com.example.smarthomefinale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class survaillance extends Activity {
    private int quantity =0 ;
    private int quantity1=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survaiallnce);

    }
    public void increment(View view){
        quantity ++;
        display(quantity);
    }
    public void decrement(View view){
        quantity --;
        display(quantity);
    }
    public void increment1(View view){
        quantity1 ++;
        display(quantity);
    }
    public void decrement1(View view){
        quantity1 --;
        display(quantity);
    }
    public void display (int number){
        TextView quantityTextView =(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);

        TextView quantityTextView1 =(TextView) findViewById(R.id.quantity1_text_view);
        quantityTextView.setText(""+number);
    }

}
