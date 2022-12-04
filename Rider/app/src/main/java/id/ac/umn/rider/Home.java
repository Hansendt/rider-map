package id.ac.umn.rider;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import id.ac.umn.rider.databinding.ActivityMainBinding;

public class Home extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    FirebaseStorage storage = FirebaseStorage.getInstance();

    TextView usernameTextView;
    String username;
    String vehicleNameString, vehicleBrandString, vehicleModelString, vehicleYearString, vehicleColorString, vehiclePlateString, cylinderCapacityString, vehicleFrameNumberString;
    String isBikeCreated;
    boolean isBikeCreatedBoolean;
    ArrayList<Reminder> reminderList;
//    Button profile;
    ImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        usernameTextView = findViewById(R.id.userName);
        username = getIntent().getStringExtra("username");
        usernameTextView.setText("Hello, " + username + "!");

//        profile = findViewById(R.id.profile);
        profilePicture = findViewById(R.id.profilePicture);

//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.with(Home.this)
//                        .crop()
//                        .start();
//            }
//        });

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isBikeCreated = snapshot.child(username).child("isBikeCreated").getValue(String.class);
                isBikeCreatedBoolean = Boolean.parseBoolean(isBikeCreated);
                if (isBikeCreatedBoolean) {
                    vehicleNameString = snapshot.child(username).child("vehicleName").getValue(String.class);
                    vehicleBrandString = snapshot.child(username).child("vehicleBrand").getValue(String.class);
                    vehicleModelString = snapshot.child(username).child("vehicleModel").getValue(String.class);
                    vehicleYearString = snapshot.child(username).child("vehicleYear").getValue(String.class);
                    vehicleColorString = snapshot.child(username).child("vehicleColor").getValue(String.class);
                    vehiclePlateString = snapshot.child(username).child("vehiclePlate").getValue(String.class);
                    cylinderCapacityString = snapshot.child(username).child("cylinderCapacity").getValue(String.class);
                    vehicleFrameNumberString = snapshot.child(username).child("vehicleFrameNumber").getValue(String.class);

                    BikeCreatedFragment bikeCreatedFragment = new BikeCreatedFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("vehicleName", vehicleNameString);
                    bundle.putString("vehicleBrand", vehicleBrandString);
                    bundle.putString("vehicleModel", vehicleModelString);
                    bundle.putString("vehicleColor", vehicleColorString);
                    bundle.putString("vehicleYear", vehicleYearString);
                    bundle.putString("vehiclePlate", vehiclePlateString);
                    bundle.putString("cylinderCapacity", cylinderCapacityString);
                    bundle.putString("vehicleFrameNumber", vehicleFrameNumberString);
                    bikeCreatedFragment.setArguments(bundle);
                    transaction.replace(R.id.frameLayout, bikeCreatedFragment);
                    transaction.commit();
                }
                else{
                    replaceFragment(new NoBikeFragment());
                    replaceFragmentReminder(new NoReminderFragment());
                }
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
//        if (reminder.size() == 0) {
//            replaceFragmentReminder(new ReminderFragment());
//        }

    }

//    public void changeProfilePicture(View view) {
//        ImagePicker.with(Home.this)
//                .crop()
//                .start();
//    }

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
        intent.putExtra("username", username);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        profilePicture.setImageURI(uri);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(username).exists()) {
                            vehicleNameString = snapshot.child(username).child("vehicleName").getValue().toString();
                            vehicleBrandString = snapshot.child(username).child("vehicleBrand").getValue().toString();
                            vehicleModelString = snapshot.child(username).child("vehicleModel").getValue().toString();
                            vehicleYearString = snapshot.child(username).child("vehicleYear").getValue().toString();
                            vehicleColorString = snapshot.child(username).child("vehicleColor").getValue().toString();
                            vehiclePlateString = snapshot.child(username).child("vehiclePlate").getValue().toString();
                            cylinderCapacityString = snapshot.child(username).child("cylinderCapacity").getValue().toString();
                            vehicleFrameNumberString = snapshot.child(username).child("vehicleFrameNumber").getValue().toString();

                            BikeCreatedFragment bikeCreatedFragment = new BikeCreatedFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putString("vehicleName", vehicleNameString);
                            bundle.putString("vehicleBrand", vehicleBrandString);
                            bundle.putString("vehicleModel", vehicleModelString);
                            bundle.putString("vehicleColor", vehicleColorString);
                            bundle.putString("vehicleYear", vehicleYearString);
                            bundle.putString("vehiclePlate", vehiclePlateString);
                            bundle.putString("cylinderCapacity", cylinderCapacityString);
                            bundle.putString("vehicleFrameNumber", vehicleFrameNumberString);
                            bikeCreatedFragment.setArguments(bundle);
                            transaction.replace(R.id.frameLayout, bikeCreatedFragment);
                            transaction.commit();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public void bikeInfo(View view) {
        Intent intentBike = new Intent(this, BikeDetail.class);
        startActivity(intentBike);
    }
}