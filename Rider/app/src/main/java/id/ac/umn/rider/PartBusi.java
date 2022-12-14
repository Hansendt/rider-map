package id.ac.umn.rider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PartBusi extends AppCompatActivity {
    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;
    String tanggalTerakhir, tanggalOptimal;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_list_part_busi);

        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);
        mAuth = FirebaseAuth.getInstance();

        updateTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PartBusi.this, R.style.datePicker, (view1, year, month, dayOfMonth) -> {
                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    int monthInt = month + 5;
                    if (monthInt <= 12) {
                        tanggalBaru.setText(dayOfMonth + "/" + (monthInt) + "/" + (year+1));
                    }else {
                        int monthMod = monthInt % 12;
                        int x = (monthInt - 2)/ 12;
                        tanggalBaru.setText(dayOfMonth + "/" + (monthMod) + "/" + (year+x));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
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

        tanggalTerakhir = tanggalLama.getText().toString();
        tanggalOptimal = tanggalBaru.getText().toString();

        int image = R.drawable.busi;

        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Busi").child("date").setValue(tanggalTerakhir);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Busi").child("dateOptimal").setValue(tanggalOptimal);
        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("Reminder").child("Busi").child("image").setValue(image);

        Intent intent = new Intent(PartBusi.this, ListPart.class);
        startActivity(intent);
        finish();
    }
}