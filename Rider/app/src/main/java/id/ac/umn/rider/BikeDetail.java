package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BikeDetail extends AppCompatActivity {

    ActionBar actionBar;
    Button btnList, btnPajak, back;
    TextView bikeName, bikeBrand, bikeModel, bikePlate, bikeYear, bikeCC, bikeFrameNum;
    ImageView bikeImage;
    String userUID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.back);

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_bike_detail);
        bikeName = findViewById(R.id.name);
        bikeBrand = findViewById(R.id.brand);
        bikeModel = findViewById(R.id.model);
        bikePlate = findViewById(R.id.plate);
        bikeYear = findViewById(R.id.year);
        bikeCC = findViewById(R.id.cc);
        bikeImage = findViewById(R.id.image);
        bikeFrameNum = findViewById(R.id.frameNum);
        btnList = findViewById(R.id.listPartButton);
        btnPajak = findViewById(R.id.pajakButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userUID = user.getUid();

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bikeName.setText(snapshot.child(userUID).child("Bike").child("vehicleName").getValue().toString());
                bikeImage.setImageResource(snapshot.child(userUID).child("Bike").child("vehiclePhoto").getValue(Integer.class));
                bikeBrand.setText(snapshot.child(userUID).child("Bike").child("vehicleBrand").getValue().toString());
                bikeModel.setText(snapshot.child(userUID).child("Bike").child("vehicleModel").getValue().toString());
                bikePlate.setText(snapshot.child(userUID).child("Bike").child("vehiclePlate").getValue().toString());
                bikeYear.setText(snapshot.child(userUID).child("Bike").child("vehicleYear").getValue().toString());
                bikeCC.setText(snapshot.child(userUID).child("Bike").child("cylinderCapacity").getValue().toString() + " cc");
                bikeFrameNum.setText(snapshot.child(userUID).child("Bike").child("vehicleFrameNumber").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

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
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}