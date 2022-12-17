package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class PajakKendaraan extends AppCompatActivity {

    ArrayList<Reminder> reminderList;

    TextView tanggalLama, tanggalBaru;
    ImageView bikeImage;
    Button updateTanggal;
    String tanggalTerakhir, tanggalOptimal, userUID;
    FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_pajak_kendaraan);
        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);
        bikeImage = findViewById(R.id.bikeImagePajak);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userUID = user.getUid();



        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bikeImage.setImageResource(snapshot.child(userUID).child("Bike").child("vehiclePhoto").getValue(Integer.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        updateTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PajakKendaraan.this, (view1, year, month, dayOfMonth) -> {
                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    tanggalBaru.setText(dayOfMonth + "/" + (month + 1) + "/" + (year+1));
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
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

    public void confirm(View view) {

        int image = R.drawable.pajak_kendaraan;

        tanggalTerakhir = tanggalLama.getText().toString();
        tanggalOptimal = tanggalBaru.getText().toString();
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").child("date").setValue(tanggalTerakhir);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").child("dateOptimal").setValue(tanggalOptimal);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").child("image").setValue(image);



        Intent intent = new Intent(PajakKendaraan.this, BikeDetail.class);
        startActivity(intent);
        finish();
    }
}