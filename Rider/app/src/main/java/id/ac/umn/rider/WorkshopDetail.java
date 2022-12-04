package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WorkshopDetail extends AppCompatActivity {

    TextView name,  rate, dist, description, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_workshop_detail);

        name = findViewById(R.id.nama);
        description = findViewById(R.id.desc);
        rate = findViewById(R.id.rating);
//        description = findViewById(R.id.jarak);
        phone = findViewById(R.id.telp);

        String nama = getIntent().getStringExtra("nama");
        String desc = getIntent().getStringExtra("desc");
        String jarak = getIntent().getStringExtra("jarak");
        String rating = getIntent().getStringExtra("rating");
        String telp = getIntent().getStringExtra("telp");

        name.setText(nama);
        rate.setText(rating);
//        dist.setText(jarak);
        description.setText(desc);
        phone.setText(telp);

    }


}