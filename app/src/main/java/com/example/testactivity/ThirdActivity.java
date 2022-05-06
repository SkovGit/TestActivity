package com.example.testactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private Button btn_closeThird, btn_addElement;
    private TextView txt_msgThrid;
    private ListView lst_myList;
    private EditText ed_newElement;
    private Intent intent;
    private ArrayList<String> list;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list",list);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btn_closeThird = findViewById(R.id.btn_closeThird);
        btn_addElement = findViewById(R.id.btn_addElement);
        lst_myList = findViewById(R.id.lst_myList);
        if (savedInstanceState == null) {
            list = new ArrayList<>();
        } else {
            list = savedInstanceState.getStringArrayList("list");
        }
       /* list.add("Købe Mælk");
        list.add("Vaske Bil");
        list.add("Hente Børn");
        list.add("Lave Mad"); */

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        lst_myList.setAdapter(arrayAdapter);

        ed_newElement = findViewById(R.id.ed_newElement);
        intent = getIntent();
        txt_msgThrid = findViewById(R.id.txt_msgThird);

        txt_msgThrid.setText(intent.getStringExtra("fromMain"));
        btn_closeThird.setOnClickListener( view -> {
            intent.putExtra("msg", "Hilsen fra Third");
            setResult(AppConstants.RESULT_CODE_THIRD, intent);
            finish();
        });

        btn_addElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (ed_newElement.getText().toString().equals("")) return;
                if (ed_newElement.getText() != null) {
                    String newElement = ed_newElement.getText().toString();
                    list.add(newElement);
                    arrayAdapter.notifyDataSetChanged();
                    ed_newElement.setText("");
                } else {
                    Toast.makeText(ThirdActivity.this, "kan ikke tilføje ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lst_myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                list.remove(pos);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}