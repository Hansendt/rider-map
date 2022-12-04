package id.ac.umn.rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListPart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_list_part);
    }

    public void banDepan(View view) {
        Intent intent = new Intent(ListPart.this, ListPartExt.class);
        startActivity(intent);
    }

    public void toDetail(View view) {
        Intent intent = new Intent(ListPart.this, BikeDetail.class);
        startActivity(intent);
    }

}