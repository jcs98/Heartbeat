package com.example.jcs.heartbeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    EditText ageEditText;

    LinearLayout rhrLinearLayout;
    TextView rhrTextView;

    LinearLayout thrLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ageEditText = (EditText) findViewById(R.id.et_age);
        rhrLinearLayout = (LinearLayout) findViewById(R.id.ll_restingHeartRate);
        rhrTextView = (TextView) findViewById(R.id.tv_restingHeartRate);
        thrLinearLayout = (LinearLayout) findViewById(R.id.ll_targetHeartRate);

        rhrLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRestingHeartRate();
            }
        });
        thrLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTargetHeartRate();
            }
        });
    }

    private void calculateTargetHeartRate() {
        String age = ageEditText.getText().toString();
        if(age.matches("")){
            Toast.makeText(this, "Please enter your age!", Toast.LENGTH_SHORT).show();
            return;
        }

        String restingHeartRate = rhrTextView.getText().toString();
        if(restingHeartRate.matches("")){
            Toast.makeText(this, "Please calculate your RHR first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentToCalculateTHR = new Intent(Home.this, MainActivity.class);

        intentToCalculateTHR.putExtra("age", age);
        intentToCalculateTHR.putExtra("restingHeartRate", restingHeartRate);

        startActivity(intentToCalculateTHR);
    }

    public void getRestingHeartRate(){
        Intent intentToGetRHR = new Intent(Home.this, MainActivity.class);
        startActivityForResult(intentToGetRHR, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            String restingHeartRate = data.getStringExtra("restingHeartRate");
            rhrTextView.setText(restingHeartRate);
        }
    }
}
