package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    TextView bikeName, bikeBrand, bikeModel, bikeYear, bikeCC, bikeFrameNum;
    String userUID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bike_detail);
        bikeName = findViewById(R.id.name);
        bikeBrand = findViewById(R.id.brand);
        bikeModel = findViewById(R.id.model);
        bikeYear = findViewById(R.id.year);
        bikeCC = findViewById(R.id.cc);
        bikeFrameNum = findViewById(R.id.frameNum);
        btnList = findViewById(R.id.listPartButton);
        btnPajak = findViewById(R.id.pajakButton);
        back = findViewById(R.id.toHome);
        user = FirebaseAuth.getInstance().getCurrentUser();

        userUID = user.getUid();

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String cc = snapshot.child(userUID).child("Bike").child("cylinderCapacity").getValue().toString() + " cc";
                bikeName.setText(snapshot.child(userUID).child("Bike").child("vehicleName").getValue().toString());
                bikeBrand.setText(snapshot.child(userUID).child("Bike").child("vehicleBrand").getValue().toString());
                bikeModel.setText(snapshot.child(userUID).child("Bike").child("vehicleModel").getValue().toString());
                bikeYear.setText(snapshot.child(userUID).child("Bike").child("vehicleYear").getValue().toString());
                bikeCC.setText(cc);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BikeDetail.this, Home.class);
                startActivityForResult(intent, 2);
            }
        });
    }

}