package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PajakKendaraan extends AppCompatActivity {

    ArrayList<Reminder> reminderList;

    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;

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
                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    tanggalBaru.setText(dayOfMonth + "/" + (month + 1) + "/" + (year+5));
                }, 2021, 0, 1);
                datePickerDialog.show();
            }
        });
    }

//    public void update(View view) {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
//                String date = day + "/" + (month + 1) + "/" + year;
//                String date2 = day + "/" + (month + 1) + "/" + (year+5);
////                Intent intent = new Intent(PajakKendaraan.this, Reminder.class);
////                intent.putExtra("date", date);
////                startActivity(intent);
//                tanggalLama.setText(date);
//                tanggalBaru.setText(date2);
//            }
//        }, 2021, 0, 1);
//    }

    public void confirm(View view) {
        Intent intent = new Intent(PajakKendaraan.this, BikeDetail.class);
//        Bundle args = new Bundle();
//        reminderList.add(new Reminder("pajak", "20 Mei", "20 Mei"));
//        args.putSerializable("ARRAYLIST",(ArrayList<Reminder>) reminderList);
//        intent.putExtra("BUNDLE",args);
        intent.putExtra("methodName", "addReminder");
        startActivity(intent);
        finish();
    }
}