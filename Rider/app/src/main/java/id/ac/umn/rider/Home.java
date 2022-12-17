package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");
    StorageReference storage = FirebaseStorage.getInstance().getReference();
    private FirebaseUser user;

    TextView usernameTextView;
    String userUID;
    String vehicleNameString;
    String vehicleBrandString;
    String vehicleModelString;
    String vehicleYearString;
    String vehicleColorString;
    String vehiclePlateString;
    String cylinderCapacityString;
    String vehicleFrameNumberString;
    Integer vehiclePhoto;
    String isBikeCreated;
    boolean isBikeCreatedBoolean;
    ImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userUID = user.getUid();



        usernameTextView = findViewById(R.id.userName);
        if (user.getDisplayName() != null) {
            usernameTextView.setText("Hello, " + user.getDisplayName() + "!");
        }

        profilePicture = findViewById(R.id.profilePicture);
        Uri uri = user.getPhotoUrl();
        if (uri != null) {
            Picasso.get().load(uri).into(profilePicture);
        }


        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isBikeCreated = snapshot.child(userUID).child("isBikeCreated").getValue(String.class);
                isBikeCreatedBoolean = Boolean.parseBoolean(isBikeCreated);
                if (isBikeCreatedBoolean) {
                    vehicleNameString = snapshot.child(userUID).child("Bike").child("vehicleName").getValue(String.class);
                    vehicleBrandString = snapshot.child(userUID).child("Bike").child("vehicleBrand").getValue(String.class);
                    vehicleModelString = snapshot.child(userUID).child("Bike").child("vehicleModel").getValue(String.class);
                    vehicleYearString = snapshot.child(userUID).child("Bike").child("vehicleYear").getValue(String.class);
                    vehicleColorString = snapshot.child(userUID).child("Bike").child("vehicleColor").getValue(String.class);
                    vehiclePlateString = snapshot.child(userUID).child("Bike").child("vehiclePlate").getValue(String.class);
                    cylinderCapacityString = snapshot.child(userUID).child("Bike").child("cylinderCapacity").getValue(String.class);
                    vehicleFrameNumberString = snapshot.child(userUID).child("Bike").child("vehicleFrameNumber").getValue(String.class);
                    vehiclePhoto = snapshot.child(userUID).child("Bike").child("vehiclePhoto").getValue(Integer.class);


                    if (snapshot.child(userUID).child("Reminder").exists()) {
                        replaceFragmentReminder(new ReminderFragment());
                    } else{
                        replaceFragmentReminder(new NoReminderFragment());
                    }
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
                    bundle.putInt("vehiclePhoto", vehiclePhoto);
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
                        startActivity(new Intent(getApplicationContext(),SpbuMap.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.workshop:
                        startActivity(new Intent(getApplicationContext(),WorkshopMap.class));
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

    }

    public void changeProfilePicture(View view) {
        com.github.dhaval2404.imagepicker.ImagePicker.with(Home.this)
                .crop()	//User can only select image from Gallery
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            profilePicture.setImageURI(uri);
            user.updateProfile(new UserProfileChangeRequest.Builder().setPhotoUri(uri).build());

        } else if (resultCode == com.github.dhaval2404.imagepicker.ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, com.github.dhaval2404.imagepicker.ImagePicker.RESULT_ERROR, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(userUID).exists()) {
                            vehicleNameString = snapshot.child(userUID).child("Bike").child("vehicleName").getValue().toString();
                            vehicleBrandString = snapshot.child(userUID).child("Bike").child("vehicleBrand").getValue().toString();
                            vehicleModelString = snapshot.child(userUID).child("Bike").child("vehicleModel").getValue().toString();
                            vehicleYearString = snapshot.child(userUID).child("Bike").child("vehicleYear").getValue().toString();
                            vehicleColorString = snapshot.child(userUID).child("Bike").child("vehicleColor").getValue().toString();
                            vehiclePlateString = snapshot.child(userUID).child("Bike").child("vehiclePlate").getValue().toString();
                            cylinderCapacityString = snapshot.child(userUID).child("Bike").child("cylinderCapacity").getValue().toString();
                            vehicleFrameNumberString = snapshot.child(userUID).child("Bike").child("vehicleFrameNumber").getValue().toString();
                            vehiclePhoto = snapshot.child(userUID).child("Bike").child("vehiclePhoto").getValue(Integer.class);

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
                            bundle.putInt("vehiclePhoto", vehiclePhoto);
                            bikeCreatedFragment.setArguments(bundle);
                            transaction.replace(R.id.frameLayout, bikeCreatedFragment);
                            transaction.commitAllowingStateLoss();
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
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void createBike(View view) {
        Intent intent = new Intent(this, CreateBikeActivity.class);
        startActivityForResult(intent, 1);
    }


    public void bikeInfo(View view) {
        Intent intentBike = new Intent(this, BikeDetail.class);
        startActivity(intentBike);
    }

    protected void onRestart() {
        super.onRestart();
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userUID).child("Reminder").exists()) {
                    replaceFragmentReminder(new ReminderFragment());
                } else{
                    replaceFragmentReminder(new NoReminderFragment());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}