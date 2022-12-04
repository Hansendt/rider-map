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
import android.widget.Button;
import android.widget.EditText;

public class CreateBikeActivity extends AppCompatActivity implements View.OnClickListener {

//    ActionBar actionBar;
    Button bikeCreated;
    EditText vehicleName, vehicleBrand, vehicleModel, vehicleYear, vehicleColor, vehiclePlate, cylinderCapacity, vehicleFrameNumber;
    String vehicleNameString, vehicleBrandString, vehicleModelString, vehicleYearString, vehicleColorString, vehiclePlateString, cylinderCapacityString, vehicleFrameNumberString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_bike);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#171D54")));

        vehicleName = findViewById(R.id.vehicleName);
        vehicleBrand = findViewById(R.id.vehicleBrand);
        vehicleModel = findViewById(R.id.vehicleModel);
        vehicleYear = findViewById(R.id.vehicleYear);
        vehicleColor = findViewById(R.id.vehicleColor);
        vehiclePlate = findViewById(R.id.platNumber);
        cylinderCapacity = findViewById(R.id.cylinderCapacity);
        vehicleFrameNumber = findViewById(R.id.vehicleFrameNumber);
        bikeCreated = (Button)findViewById(R.id.buttonNext);
        bikeCreated.setOnClickListener(this);

    }
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    public void onBackPressed() {
//        finish();
//    }

    @Override
    public void onClick(View view) {
        vehicleNameString = vehicleName.getText().toString();
        vehicleBrandString = vehicleBrand.getText().toString();
        vehicleModelString = vehicleModel.getText().toString();
        vehicleYearString = vehicleYear.getText().toString();
        vehicleColorString = vehicleColor.getText().toString();
        vehiclePlateString = vehiclePlate.getText().toString();
        cylinderCapacityString = cylinderCapacity.getText().toString();
        vehicleFrameNumberString = vehicleFrameNumber.getText().toString();

        Intent intentNew = new Intent();
        intentNew.putExtra("vehicleName", vehicleNameString);
        intentNew.putExtra("vehicleBrand", vehicleBrandString);
        intentNew.putExtra("vehicleModel", vehicleModelString);
        intentNew.putExtra("vehicleYear", vehicleYearString);
        intentNew.putExtra("vehicleColor", vehicleColorString);
        intentNew.putExtra("vehiclePlate", vehiclePlateString);
        intentNew.putExtra("cylinderCapacity", cylinderCapacityString);
        intentNew.putExtra("vehicleFrameNumber", vehicleFrameNumberString);
        setResult(RESULT_OK, intentNew);
        finish();
    }

}