package com.enigma.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnMinus, btnMulti, btnDiv, btnDot, btnDelete, btnEquals, btnAC;
    private TextView textViewResult, textViewHistory;

    private String number = null;

    double firstNumber = 0;
    double lastNumber = 0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot = true;

    boolean btnACcontrol = true;

    boolean btnEqualControl = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMulti);
        btnDiv = findViewById(R.id.btnDivide);

        btnDot = findViewById(R.id.btnDot);
        btnDelete = findViewById(R.id.btnDel);
        btnEquals = findViewById(R.id.btnEquals);
        btnAC = findViewById(R.id.btnAC);

        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);

        btn0.setOnClickListener(view -> {
            numberClick("0");
        });

        btn1.setOnClickListener(view -> {
            numberClick("1");
        });

        btn2.setOnClickListener(view -> {
            numberClick("2");
        });

        btn3.setOnClickListener(view -> {
            numberClick("3");
        });

        btn4.setOnClickListener(view -> {
            numberClick("4");
        });

        btn5.setOnClickListener(view -> {
            numberClick("5");
        });

        btn6.setOnClickListener(view -> {
            numberClick("6");
        });

        btn7.setOnClickListener(view -> {
            numberClick("7");
        });

        btn8.setOnClickListener(view -> {
            numberClick("8");
        });

        btn9.setOnClickListener(view -> {
            numberClick("9");
        });

        btnDot.setOnClickListener(view -> {
            if (dot){
                if (number == null){
                    number = "0.";
                }
                else{
                    number = number + ".";
                }
            }



            textViewResult.setText(number);
            dot = false;
        });

        btnEquals.setOnClickListener(view -> {
            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if (Objects.equals(status, "division")){
                    divide();
                }
                else if (Objects.equals(status, "subtraction")){
                    minus();
                }
                else if (Objects.equals(status, "sum")){
                    plus();
                }
                else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
            }

            operator = false;
            btnEqualControl = false;

        });

        btnAdd.setOnClickListener(view -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "+");

            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if (Objects.equals(status, "division")){
                    divide();
                }
                else if (Objects.equals(status, "subtraction")){
                    minus();
                }
                else {
                    plus();
                }
            }

            status = "sum";
            operator = false;
            number = null;
        });

        btnMinus.setOnClickListener(view -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "-");

            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if (Objects.equals(status, "division")){
                    divide();
                }
                else if (Objects.equals(status, "sum")){
                    plus();
                }
                else {
                    minus();
                }
            }

            status = "subtraction";
            operator = false;
            number = null;
        });

        btnMulti.setOnClickListener(view -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "x");

            if (operator){
                if (Objects.equals(status, "subtraction")){
                    minus();
                }
                else if (Objects.equals(status, "division")){
                    divide();
                }
                else if (Objects.equals(status, "sum")){
                    plus();
                }
                else {
                    multiply();
                }
            }

            status = "multiplication";
            operator = false;
            number = null;
        });

        btnDiv.setOnClickListener(view -> {

            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history + currentResult + "/");

            if (operator){
                if (Objects.equals(status, "subtraction")){
                    minus();
                }
                else if (Objects.equals(status, "multiplication")){
                    divide();
                }
                else if (Objects.equals(status, "sum")){
                    plus();
                }
                else {
                    divide();
                }
            }

            status = "division";
            operator = false;
            number = null;
        });

        btnAC.setOnClickListener(view -> {

            number = null;
            status = null;
            textViewResult.setText("0");
            textViewHistory.setText("");
            firstNumber = 0;
            lastNumber = 0;
            dot = true;
            btnACcontrol = true;
        });

        btnDelete.setOnClickListener(view -> {

            if (btnACcontrol){
                textViewResult.setText("0");
            }
            else{
                number = number.substring(0,number.length()-1);

                if (number.length() == 0){
                    btnDelete.setClickable(false);
                }
                else if (number.contains(".")){
                    dot = false;
                }
                else{
                    dot = true;
                }

                textViewResult.setText(number);
            }
        });


    }

    public void numberClick(String view){
        if (number == null){
            number = view;
        } else if (btnEqualControl) {
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        } else {
            number = number + view;
        }

        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDelete.setClickable(true);
        btnEqualControl = false;
    }

    public void plus(){
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void minus(){
        if (firstNumber == 0){
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        }
        else{
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void multiply(){
        if (firstNumber == 0){
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        else{
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void divide(){
        if (firstNumber == 0){

            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber;
        }
        else{
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
}