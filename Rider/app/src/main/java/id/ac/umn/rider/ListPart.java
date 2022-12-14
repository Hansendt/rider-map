package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ListPart extends AppCompatActivity {

    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_part);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setTitle("List Part");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
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

    public void banDepan(View view) {
        Intent intent = new Intent(ListPart.this, PartBanDepan.class);
        startActivity(intent);
    }

    public void toDetail(View view) {
        Intent intent = new Intent(ListPart.this, BikeDetail.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}