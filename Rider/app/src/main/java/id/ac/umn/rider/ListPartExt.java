package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListPartExt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_part_ext);
    }

    public void confirm(View view) {
        Intent intent = new Intent(ListPartExt.this, ListPart.class);
        startActivity(intent);
        finish();
    }
}