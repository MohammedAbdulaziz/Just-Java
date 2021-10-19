/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{
    int coffee;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void inc(View view)
    {
        coffee++;
        display(coffee);
        displayPrice(coffee * 5);
        displayUpdate(getString(R.string.PRICE));
    }
    public void dec(View view)
    {
        if(coffee!=0)
        {
            coffee--;
            display(coffee);
            displayPrice(coffee * 5);
            displayUpdate(getString(R.string.PRICE));
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        int price = coffee * 5;
        EditText customerName = findViewById(R.id.customer_name);
        customerName.setTextColor(Color.WHITE);
        name = customerName.getText().toString();
        CheckBox C = findViewById(R.id.chocolate);
        CheckBox WC = findViewById(R.id.whippedCream);
        boolean checkC = C.isChecked();
        boolean checkWC = WC.isChecked();
        if(checkWC)
        {
            price+=1;
        }
        if(checkC)
        {
            price+=2;
        }
        String priceMessage;
        if(coffee!= 0) {
            String pMsg = String.format("Name: %s\nquantity: %d cups of coffee\nAdd Whipping Cream? %s", name, coffee, checkWC+"");
            Log.v("naaahice", pMsg);
            priceMessage = getString(R.string.name) + ": " + name + "\n" + getString(R.string.quantity) +": " + coffee + " " + getString(R.string.cupsOfCoffee) + "\n" + getString(R.string.addWhippedCream) + "? " + checkWC + "\n" + getString(R.string.addChocolate) + "? " + checkC + "\n" + getString(R.string.total) + ": $" + price + "\n" + getString(R.string.thankYou) +" !";
            displayMessage(priceMessage);
            displayUpdate(getString(R.string.orderSummary));
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            intent.setType("text/plain");
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
        else{
            Toast toast = new Toast(this);
            toast.makeText(this,getString(R.string.pleaseQuantity),Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number)
    {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    @SuppressLint("NewApi")
    private void displayPrice(int number)
    {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * is the number of cups of coffee ordered
     */
    private int calculatePrice()
    {
        int price = coffee * 5;
        return price;
    }
    private void displayUpdate(String update)
    {
        TextView orderSummaryUpdate = (TextView) findViewById(R.id.orderSummaryUpdateID);
        orderSummaryUpdate.setText(update);
    }
}