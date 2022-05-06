package com.example.testactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_goToSecond, btn_goToThird;
    private TextView txt_msg;
    private ActivityResultLauncher<Intent> launcher;

    /* Note to parse HexString to int
    //Method for Smaller Number Range:
    Integer.parseInt("abc",16);

    //Method for Bigger Number Range.
    Long.parseLong("abc",16);

    //Method for Biggest Number Range.
    new BigInteger("abc",16);
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_goToSecond = findViewById(R.id.btn_goToSecond);
        btn_goToThird = findViewById(R.id.btn_goToThird);
        txt_msg = findViewById(R.id.txt_msg);



        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AppConstants.RESULT_CODE_SECOND) {
                            //TODO tilføk kode der behnadler resultatet af SecondActivity
                            Intent intent = result.getData();
                            txt_msg.setText(intent.getStringExtra("msg"));
                            Toast.makeText(MainActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_LONG).show();
                            return;
                        }

                        if (result.getResultCode() == AppConstants.RESULT_CODE_THIRD) {
                            //TODO tilføk kode der behnadler resultatet af ThirdActivity
                            txt_msg.setText(result.getData().getStringExtra("msg"));
                            //Toast.makeText(MainActivity.this, "******", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
        );
        btn_goToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("fromMain", "Besked fra main");
                launcher.launch(intent);
            }
        });
        btn_goToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("fromMain","Besked fra main");
                launcher.launch((intent));
            }
        });
    }
}