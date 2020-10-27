package com.example.bvtn1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    String tien[] = {
            "Việt Nam Đồng",
            "Yên Nhật",
            "Bảng Anh",
            "Đô la Mỹ",
            "Nhân dân tệ TQ",
            "Euro",
            "Bath Thái Lan",
            "Rúp Nga",
            "Won Hàn Quốc",
            "Peso Pinoy"
    };
    float tigia[] = {
            (float) 1.0,
            (float) 221.48,
            (float) 30229.75,
            (float) 23180.34,
            (float) 3455.43,
            (float) 27397.26,
            (float) 742.39,
            (float) 303.58,
            (float) 20.58,
            (float) 479.85,
    };
    int d, d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText text = findViewById(R.id.value);
        final TextView result = findViewById(R.id.result);
        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);

        final ArrayAdapter<String> adaper1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tien);
        ArrayAdapter<String> adaper2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tien);
        adaper1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adaper2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner1.setAdapter(adaper1);
        spinner2.setAdapter(adaper2);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, tien[position], Toast.LENGTH_SHORT);
                d = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                result.setText("");
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, tien[position], Toast.LENGTH_SHORT);
                d2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                result.setText("");
            }
        });
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    float tiền_gốc = Float.parseFloat(text.getText().toString());
                    float kết_quả = (float) tiền_gốc * tigia[d] / tigia[d2];
                    DecimalFormat df = new DecimalFormat("#.00");
                    String sss = df.format(kết_quả);
                    result.setText(String.valueOf(tiền_gốc) + " " + tien[d] + " = " + sss + " " + tien[d2]);
                }
                catch (Exception e) {
                    result.setText("Vui lòng nhập số tiền");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                text.setError(null);
            }
        });
    }
}