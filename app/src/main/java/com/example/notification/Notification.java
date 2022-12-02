package com.example.notification;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification extends AppCompatActivity {

    private EditText eStart;
    private EditText eEnd;
    private EditText eTime;
    private EditText eStation;
    private Button channel1Btn;
    private NotificationHelper mNotificationhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eStart = findViewById(R.id.edit_start);
        eEnd = findViewById(R.id.edit_end);
        eTime = findViewById(R.id.edit_time);
        eStation = findViewById(R.id.edit_station);
        channel1Btn = findViewById(R.id.btn_channel1);

        mNotificationhelper = new NotificationHelper(this);

        channel1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = eStart.getText().toString();
                String end = eEnd.getText().toString();
                String time = eTime.getText().toString();
                String station = eStation.getText().toString();

                sendOnChannel1(start + " 승차", "목적지 : " + end, "오후(오전) " + time + " 도착 예정", "정류장 " + station +"개 남음");
            }
        });
    }

    public void sendOnChannel1(String start, String end, String time, String station) {
        NotificationCompat.Builder nb = mNotificationhelper.getChannel1Notification(start, end, time, station);
        mNotificationhelper.getManager().notify(1, nb.build());
    }
}
