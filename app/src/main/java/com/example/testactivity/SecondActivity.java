package com.example.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private Button btn_closeSecond;
    private TextView txt_msgSecond;
    private Intent intent;
    private Spinner spn_drink, spn_snacks;
    private ArrayList<String> drinks = new ArrayList<>();
    private String ditValg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        drinks.add("Vælg fra listen");
        drinks.add("Coffee");
        drinks.add("Soda");
        drinks.add("Juice");
        drinks.add("Water");
        drinks.add("Soup");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drinks);

        intent = getIntent();
        spn_drink = findViewById(R.id.spn_drink);
        spn_snacks = findViewById(R.id.spn_snacks);
        txt_msgSecond = findViewById(R.id.txt_msgSecond);

        txt_msgSecond.setText(intent.getStringExtra("fromMain"));

        spn_drink.setAdapter(adapter);
        spn_snacks.setAdapter(adapter);
        spn_drink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
               if (pos == 0){ return; }
                ditValg = drinks.get(pos);
                Toast.makeText(SecondActivity.this, "Dit valg: " + drinks.get(pos), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        btn_closeSecond = findViewById(R.id.btn_closeSecond);
        btn_closeSecond.setOnClickListener( view -> {

            intent.putExtra("msg", ditValg);
            setResult(AppConstants.RESULT_CODE_SECOND, intent);
            finish();
        });
    }
}