package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListPartExt extends AppCompatActivity {
    TextView tanggalLama, tanggalBaru;
    Button updateTanggal;
    String tanggalTerakhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_part_ext);

        tanggalLama = findViewById(R.id.editText);
        tanggalBaru = findViewById(R.id.tanggalBaru);
        updateTanggal = findViewById(R.id.update);

        updateTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ListPartExt.this, (view1, year, month, dayOfMonth) -> {
                    tanggalLama.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    tanggalBaru.setText(dayOfMonth + "/" + (month + 1) + "/" + (year+5));
                }, 2021, 0, 1);
                datePickerDialog.show();
            }
        });
    }

    public void confirm(View view) {
        Intent intent = new Intent(ListPartExt.this, ListPart.class);
        startActivity(intent);
        finish();
    }
}