package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class PajakKendaraan extends AppCompatActivity {

    ArrayList<Reminder> reminderList;

    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;
    String tanggalTerakhir, tanggalOptimal;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pajak_kendaraan);
        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);
        mAuth = FirebaseAuth.getInstance();

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

    public void confirm(View view) {

        int image = R.drawable.pajak_kendaraan;

        tanggalTerakhir = tanggalLama.getText().toString();
        tanggalOptimal = tanggalBaru.getText().toString();
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").child("date").setValue(tanggalTerakhir);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").child("dateOptimal").setValue(tanggalOptimal);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Air Radiator").child("image").setValue(image);



        Intent intent = new Intent(PajakKendaraan.this, BikeDetail.class);
        startActivity(intent);
        finish();
    }
}