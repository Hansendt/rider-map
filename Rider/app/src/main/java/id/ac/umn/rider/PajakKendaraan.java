package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PajakKendaraan extends AppCompatActivity {

    ArrayList<Reminder> reminderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pajak_kendaraan);
    }

    public void confirm(View view) {
        Intent intent = new Intent(PajakKendaraan.this, BikeDetail.class);
//        Bundle args = new Bundle();
//        reminderList.add(new Reminder("pajak", "20 Mei", "20 Mei"));
//        args.putSerializable("ARRAYLIST",(ArrayList<Reminder>) reminderList);
//        intent.putExtra("BUNDLE",args);
        intent.putExtra("methodName", "addReminder");
        startActivity(intent);
        finish();
    }
}