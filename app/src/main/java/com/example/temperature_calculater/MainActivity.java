package com.example.temperature_calculater;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input_number;
    Button submit_btn;
    TextView result;
    private static DecimalFormat decimalFormatter = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_number = findViewById(R.id.input_number);
        submit_btn = findViewById(R.id.submit_btn);
        result = findViewById(R.id.result);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(input_number.getText().toString().trim())) {
                    input_number.setError("Please Enter the Number of Chrips");
                    return;
                }
                else if (onlyDigits(input_number.getText().toString().trim())){
                    String str_input_number = input_number.getText().toString().trim();
                    double number_of_chirps = Integer.parseInt(str_input_number);
                    double temperature = 0;
                    temperature = ((number_of_chirps / 3) + 4);
                    decimalFormatter.setRoundingMode(RoundingMode.DOWN);
                    result.setText(getString(R.string.final_result_text) + String.valueOf(decimalFormatter.format(temperature)) + getString(R.string.degree_celsius_symbol));
                    result.setVisibility(View.VISIBLE);
                }
                else {
                    input_number.setError("Please Enter the correct input format");
                    return;
                }
            }
        });
    }

    public static boolean onlyDigits(String inputString) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (inputString == null) {
            return false;
        }
        Matcher m = p.matcher(inputString);
        return m.matches();
    }
}