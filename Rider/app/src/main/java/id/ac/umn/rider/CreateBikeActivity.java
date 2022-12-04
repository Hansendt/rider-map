package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateBikeActivity extends AppCompatActivity implements View.OnClickListener {

//    ActionBar actionBar;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    Button bikeCreated;
    EditText vehicleName, vehicleModel, vehicleYear, vehicleColor, vehiclePlate, cylinderCapacity, vehicleFrameNumber;
    String vehicleNameString, vehicleBrandString, vehicleModelString, vehicleYearString, vehicleColorString, vehiclePlateString, cylinderCapacityString, vehicleFrameNumberString;
    AutoCompleteTextView vehicleBrand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_bike);

        vehicleName = findViewById(R.id.vehicleName);
//        vehicleBrand = findViewById(R.id.vehicleBrand);
        vehicleModel = findViewById(R.id.vehicleModel);
        vehicleYear = findViewById(R.id.vehicleYear);
        vehicleColor = findViewById(R.id.vehicleColor);
        vehiclePlate = findViewById(R.id.platNumber);
        cylinderCapacity = findViewById(R.id.cylinderCapacity);
        vehicleFrameNumber = findViewById(R.id.vehicleFrameNumber);
        bikeCreated = (Button)findViewById(R.id.buttonNext);
        bikeCreated.setOnClickListener(this);

        vehicleBrand = findViewById(R.id.vehicleBrand);

        String[] Brand = new String[]{"Honda", "Yamaha", "Kawasaki", "Suzuki", "Benelli", "KTM", "BMW", "Royal Enfield"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, Brand);
        vehicleBrand.setAdapter(adapter);

        vehicleBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + vehicleBrand.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//            // An item was selected. You can retrieve the selected item using
//            parent.getItemAtPosition(pos)
//        }
    }




    @Override
    public void onClick(View view) {
        final String username = getIntent().getStringExtra("username");
        vehicleNameString = vehicleName.getText().toString();
        vehicleBrandString = vehicleBrand.getText().toString();
        vehicleModelString = vehicleModel.getText().toString();
        vehicleYearString = vehicleYear.getText().toString();
        vehicleColorString = vehicleColor.getText().toString();
        vehiclePlateString = vehiclePlate.getText().toString();
        cylinderCapacityString = cylinderCapacity.getText().toString();
        vehicleFrameNumberString = vehicleFrameNumber.getText().toString();

        if (vehicleNameString.isEmpty() || vehicleBrandString.isEmpty() || vehicleModelString.isEmpty() || vehicleYearString.isEmpty() || vehicleColorString.isEmpty() || vehiclePlateString.isEmpty() || cylinderCapacityString.isEmpty() || vehicleFrameNumberString.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child("Users").child(username).child("vehicleName").setValue(vehicleNameString);
                    databaseReference.child("Users").child(username).child("vehicleBrand").setValue(vehicleBrandString);
                    databaseReference.child("Users").child(username).child("vehicleModel").setValue(vehicleModelString);
                    databaseReference.child("Users").child(username).child("vehicleYear").setValue(vehicleYearString);
                    databaseReference.child("Users").child(username).child("vehicleColor").setValue(vehicleColorString);
                    databaseReference.child("Users").child(username).child("vehiclePlate").setValue(vehiclePlateString);
                    databaseReference.child("Users").child(username).child("cylinderCapacity").setValue(cylinderCapacityString);
                    databaseReference.child("Users").child(username).child("vehicleFrameNumber").setValue(vehicleFrameNumberString);
                    databaseReference.child("Users").child(username).child("isBikeCreated").setValue("true");

                    Toast.makeText(CreateBikeActivity.this, "Bike Created", Toast.LENGTH_SHORT).show();
                    Intent intentNew = new Intent();
                    setResult(RESULT_OK, intentNew);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

}