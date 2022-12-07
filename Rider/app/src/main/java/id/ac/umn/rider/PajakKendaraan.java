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

public class PajakKendaraan extends AppCompatActivity {

    ArrayList<Reminder> reminderList;

    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;
    String tanggalBayarTerakhir, day, monthOfYear;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pajak_kendaraan);
        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);

        updateTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PajakKendaraan.this, (view1, year, month, dayOfMonth) -> {
                    if (dayOfMonth < 10){
                        day = "0" + dayOfMonth;
                    } else{
                        day = String.valueOf(dayOfMonth);
                    }

                    if (month < 10){
                        monthOfYear = "0" + month;
                    } else{
                        monthOfYear = String.valueOf(month);
                    }

                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    tanggalBaru.setText(dayOfMonth + "/" + (month + 1) + "/" + (year+1));
                }, 2021, 0, 1);
                datePickerDialog.show();
            }
        });
    }

    public void confirm(View view) {
        String tanggal = tanggalLama.getText().toString();
        tanggalBayarTerakhir = day + "/" + monthOfYear + "/" + tanggal.substring(6);
        databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Reminder").child("Pajak Kendaraan").setValue(tanggalBayarTerakhir);


        Intent intent = new Intent(PajakKendaraan.this, BikeDetail.class);
        startActivity(intent);
        finish();
    }
}