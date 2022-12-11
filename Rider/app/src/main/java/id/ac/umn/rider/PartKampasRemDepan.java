package id.ac.umn.rider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PartKampasRemDepan extends AppCompatActivity {
    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;
    String tanggalTerakhir, tanggalOptimal;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_part_kampasremdepan);

        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);
        mAuth = FirebaseAuth.getInstance();

        updateTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PartKampasRemDepan.this, (view1, year, month, dayOfMonth) -> {
                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    int monthInt = month + 22;
                    if (monthInt <= 12) {
                        tanggalBaru.setText(dayOfMonth + "/" + (monthInt) + "/" + (year+1));
                    }else {
                        int monthMod = monthInt % 12;
                        int x = (monthInt - 2)/ 12;
                        tanggalBaru.setText(dayOfMonth + "/" + (monthMod) + "/" + (year+x));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    public void confirm(View view) {

        tanggalTerakhir = tanggalLama.getText().toString();
        tanggalOptimal = tanggalBaru.getText().toString();

        int image = R.drawable.kampas_rem_depan;

        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Kampas Rem Depan").child("date").setValue(tanggalTerakhir);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Kampas Rem Depan").child("dateOptimal").setValue(tanggalOptimal);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Kampas Rem Depan").child("image").setValue(image);

        Intent intent = new Intent(PartKampasRemDepan.this, ListPart.class);
        startActivity(intent);
        finish();
    }
}