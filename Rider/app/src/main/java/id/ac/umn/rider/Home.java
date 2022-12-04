package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    FirebaseStorage storage = FirebaseStorage.getInstance();

    TextView usernameTextView;
    String username;
    String vehicleNameString, vehicleBrandString, vehicleModelString, vehicleYearString, vehicleColorString, vehiclePlateString, cylinderCapacityString, vehicleFrameNumberString;
    boolean isBikeCreated = false;
    ArrayList<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        usernameTextView = findViewById(R.id.userName);
        username = getIntent().getStringExtra("username");
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usernameTextView.setText("Hello, " + username + "!");
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.pom:
                        startActivity(new Intent(getApplicationContext(),Spbu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.workshop:
                        startActivity(new Intent(getApplicationContext(),Workshop.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

//        Toast.makeText(Home.this, "gamasuk", Toast.LENGTH_LONG).show();

        if (!isBikeCreated) {
            replaceFragment(new NoBikeFragment());
            replaceFragmentReminder(new NoReminderFragment());
        }else{
            replaceFragment(new BikeCreatedFragment());
            replaceFragmentReminder(new ReminderFragment());
        }

//        if (reminder.size() == 0) {
//            replaceFragmentReminder(new ReminderFragment());
//        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        replaceFragmentReminder(new ReminderFragment());
//        Intent intent = getIntent();
//        Bundle args = intent.getBundleExtra("BUNDLE");
////        reminder = (ArrayList<Reminder>) args.getSerializable("ARRAYLIST");
//        if (reminder.size() > 0) {
//            replaceFragmentReminder(new ReminderFragment());
//        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if(intent.getStringExtra("methodName").equals("addReminder")){
//            addReminder();
//        }
//    }

//    public void addReminder(){
//        reminderList.add(new Reminder("test", "test", "test"));
//        replaceFragmentReminder(new ReminderFragment());
//    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragmentReminder(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2, fragment);
        fragmentTransaction.commit();
    }

    public void createBike(View view) {
        Intent intent = new Intent(this, CreateBikeActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                vehicleNameString = data.getStringExtra("vehicleName");
                vehicleBrandString = data.getStringExtra("vehicleBrand");
                vehicleModelString = data.getStringExtra("vehicleModel");
                vehicleYearString = data.getStringExtra("vehicleYear");
                vehicleColorString = data.getStringExtra("vehicleColor");
                vehiclePlateString = data.getStringExtra("vehiclePlate");
                cylinderCapacityString = data.getStringExtra("cylinderCapacity");
                vehicleFrameNumberString = data.getStringExtra("vehicleFrameNumber");
                isBikeCreated = true;

                BikeCreatedFragment bikeCreatedFragment = new BikeCreatedFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("vehicleName", vehicleNameString);
                bundle.putString("vehicleBrand", vehicleBrandString);
                bundle.putString("vehicleModel", vehicleModelString);
                bundle.putString("vehicleYear", vehicleYearString);
                bundle.putString("vehiclePlate", vehiclePlateString);
                bundle.putString("cylinderCapacity", cylinderCapacityString);
                bikeCreatedFragment.setArguments(bundle);
                transaction.replace(R.id.frameLayout, bikeCreatedFragment);
                transaction.commit();
            }
        }
    }
    public void bikeInfo(View view) {
        Intent intentBike = new Intent(this, BikeDetail.class);
        startActivity(intentBike);
    }
}