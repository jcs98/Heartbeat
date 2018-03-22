package com.example.jcs.heartbeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    LinearLayout rhrLinearLayout;
    TextView rhrTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rhrLinearLayout = (LinearLayout) findViewById(R.id.ll_restingHeartRate);
        rhrTextView = (TextView) findViewById(R.id.tv_restingHeartRate);

        rhrLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRestingHeartRate();
            }
        });
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
