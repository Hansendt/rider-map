package id.ac.umn.rider;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BikeDetail extends AppCompatActivity {

    ActionBar actionBar;
    Button btnList, btnPajak, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bike_detail);
        btnList = findViewById(R.id.listPartButton);
        btnPajak = findViewById(R.id.pajakButton);
        back = findViewById(R.id.toHome);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BikeDetail.this, ListPart.class);
                startActivity(intent);
            }
        });

        btnPajak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BikeDetail.this, PajakKendaraan.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BikeDetail.this, Home.class);
                startActivityForResult(intent, 2);
            }
        });
    }

}