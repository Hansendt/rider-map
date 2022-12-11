package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateBikeActivity extends AppCompatActivity implements View.OnClickListener {

//    ActionBar actionBar;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    Button bikeCreated;
    EditText vehicleName, vehicleYear, vehicleColor, vehiclePlate, cylinderCapacity, vehicleFrameNumber;
    String vehicleNameString, vehicleBrandString, vehicleModelString, vehicleYearString, vehicleColorString, vehiclePlateString, cylinderCapacityString, vehicleFrameNumberString;
    AutoCompleteTextView vehicleBrand, vehicleModel;

    String temp;

    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_bike);

        user = FirebaseAuth.getInstance().getCurrentUser();
        vehicleName = findViewById(R.id.vehicleName);

        vehicleYear = findViewById(R.id.vehicleYear);
        vehicleColor = findViewById(R.id.vehicleColor);
        vehiclePlate = findViewById(R.id.platNumber);
        cylinderCapacity = findViewById(R.id.cylinderCapacity);
        vehicleFrameNumber = findViewById(R.id.vehicleFrameNumber);
        bikeCreated = (Button)findViewById(R.id.buttonNext);
        bikeCreated.setOnClickListener(this);

        vehicleBrand = findViewById(R.id.vehicleBrand);
        vehicleModel = findViewById(R.id.vehicleModel);

        String[] Brand = new String[]{"Honda", "Yamaha", "Kawasaki", "Suzuki", "Benelli", "KTM", "BMW", "Royal Enfield"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, Brand);
        vehicleBrand.setAdapter(adapter);

        String[] honda = new String[]{"Honda Absolute Revo","Honda Air blade","Honda Astrea","Honda Beat","Honda Blade",
                "Honda CB","Honda CB 150R","Honda CBF 600 Hornet","Honda CBR","Honda CRF250Rally","Honda CS 1","Honda kharisma",
                "Honda Kirana","Honda Legenda","Honda Little Cub","Honda Mega pro","Honda Monkey","Honda NSR","Honda PCX",
                "Honda Phantom","Honda Revo","Honda Scoopy","Honda SH150i","Honda Shadow 750","Honda Sonic","Honda Spacy",
                "Honda STX1300","Honda Supra","Honda Tiger","Honda Vario","Honda Verza","Honda Win 100","Honda Zoomer"};

        String[] yamaha = new String[]{"Yamaha As3","Yamaha Byson","Yamaha Crypton","Yamaha F 1 ZR","Yamaha Fazer","Yamaha Fino",
                "Yamaha Freego","Yamaha FZ1","Yamaha FZ6","Yamaha Grizzly","Yamaha Jupiter","Yamaha Lexam","Yamaha Lexi",
                "Yamaha Majesty","Yamaha Mio","Yamaha MT 09","Yamaha MT 25","Yamaha NMAX","Yamaha Nuovo","Yamaha R1","Yamaha R25",
                "Yamaha R6","Yamaha RX","Yamaha RZR","Yamaha Scorpio","Yamaha Sigma","Yamaha Soul GT","Yamaha T-Max 500",
                "Yamaha Tiara","Yamaha Tmax","Yamaha TW 225","Yamaha Vega R","Yamaha Vega ZR","Yamaha Vixion","Yamaha Vox",
                "Yamaha WR 250","Yamaha X-Ride","Yamaha Xabre","Yamaha Xeon","Yamaha Xmax","Yamaha YZF R15","Yamaha YZF R16",
                "Yamaha YZF R25","Yamaha YZR 125", "Yamaha Aerox"};

        String[] kawasaki = new String[]{"Kawasaki D-Tracker","Kawasaki Edge,Kawasaki ER-6N","Kawasaki Estrella","Kawasaki Tracker X",
                "Kawasaki Versys","Kawasaki Vulcan","Kawasaki W800","Kawasaki Z Series","Kawasaki Zone","Kawasaki ZR,Kawasaki ZX",
                "Kawasaki Athlete", "Kawasaki Blitz"};

        String[] suzuki = new String[]{"Suzuki Bike","Suzuki Bravo","Suzuki Cool","Suzuki Djebel","Suzuki DR","Suzuki Econos",
                "Suzuki FXR","Suzuki GS","Suzuki GSX","Suzuki Hayate","Suzuki Inazuma","Suzuki Intruder Classic","Suzuki Lets","Suzuki Nex",
                "Suzuki Raider","Suzuki Satria","Suzuki Shogun","Suzuki Shooter","Suzuki Skydrive","Suzuki Skywave","Suzuki Smash",
                "Suzuki Spin","Suzuki Thunder","Suzuki Titan","Suzuki Tornado","Suzuki Ts", "Suzuki A100", "Suzuki Arashi", "Suzuki Bandit"};

        String[] benelli = new String[]{"Benelli BN","Benelli Caffenero","Benelli Century Racer 1130","Benelli Century Racer 899","Benelli Macis",
                "Benelli New Caffenero","Benelli Patagonian Eagle","Benelli Pepe","Benelli Python","Benelli Silver blade","Benelli TNT",
                "Benelli TNT 1130","Benelli TNT 899","Benelli TNT R160","Benelli TRE-K1130","Benelli TRE-K899","Benelli X150",
                "Benelli Zafferano","Benelli Zenzero"};

        String[] ktm = new String[]{"KTM Duke","KTM Loncini","KTM Primeo","KTM RC","KTM Rock Z"};

        String[] bmw = new String[]{"BMW C 650","BMW C Evolution","BMW F 800","BMW F630GS","BMW F800S","BMW F800ST","BMW G 310",
                "BMW G650 Xchallenge","BMW G650 Xcountry","BMW G650 Xmoto","BMW HP 2 Enduro","BMW K 1600","BMW K1200GT","BMW R 1200",
                "BMW R NineT","BMW R1150RT","BMW R1200GS","BMW S 1000"};

        ArrayAdapter<String> Honda = new ArrayAdapter<>(this, R.layout.dropdown_item, honda);
        ArrayAdapter<String> Yamaha = new ArrayAdapter<>(this, R.layout.dropdown_item, yamaha);
        ArrayAdapter<String> Kawasaki = new ArrayAdapter<>(this, R.layout.dropdown_item, kawasaki);
        ArrayAdapter<String> Suzuki = new ArrayAdapter<>(this, R.layout.dropdown_item, suzuki);
        ArrayAdapter<String> Benelli = new ArrayAdapter<>(this, R.layout.dropdown_item, benelli);
        ArrayAdapter<String> KTM = new ArrayAdapter<>(this, R.layout.dropdown_item, ktm);
        ArrayAdapter<String> BMW = new ArrayAdapter<>(this, R.layout.dropdown_item, bmw);


        vehicleBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //disini masukin save db buat brand motornya

                temp = vehicleBrand.getText().toString();
                switch(temp){
                    case "Honda":
                        vehicleModel.setAdapter(Honda);
                        break;
                    case "Yamaha":
                        vehicleModel.setAdapter(Yamaha);
                        break;
                    case "Kawasaki":
                        vehicleModel.setAdapter(Kawasaki);
                        break;
                    case "Suzuki":
                        vehicleModel.setAdapter(Suzuki);
                        break;
                    case "Benelli":
                        vehicleModel.setAdapter(Benelli);
                        break;
                    case "KTM":
                        vehicleModel.setAdapter(KTM);
                        break;
                    case "BMW":
                        vehicleModel.setAdapter(BMW);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Silahkan Pilih Brand Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        vehicleModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //disini masukin save db buat model
            }
        });

    }

    @Override
    public void onClick(View view) {
        final String userUID = user.getUid();
        vehicleNameString = vehicleName.getText().toString();
        vehicleBrandString = vehicleBrand.getText().toString();
        vehicleModelString = vehicleModel.getText().toString();
        vehicleYearString = vehicleYear.getText().toString();
        vehicleColorString = vehicleColor.getText().toString();
        vehiclePlateString = vehiclePlate.getText().toString();
        cylinderCapacityString = cylinderCapacity.getText().toString();
        vehicleFrameNumberString = vehicleFrameNumber.getText().toString();

        if (vehicleNameString.isEmpty() || vehicleBrandString.isEmpty() || vehicleModelString.isEmpty() || vehicleYearString.isEmpty() || vehicleColorString.isEmpty() || vehiclePlateString.isEmpty() || cylinderCapacityString.isEmpty() || vehicleFrameNumberString.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleName").setValue(vehicleNameString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleBrand").setValue(vehicleBrandString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleModel").setValue(vehicleModelString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleYear").setValue(vehicleYearString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleColor").setValue(vehicleColorString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehiclePlate").setValue(vehiclePlateString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("cylinderCapacity").setValue(cylinderCapacityString);
                    databaseReference.child("Users").child(userUID).child("Bike").child("vehicleFrameNumber").setValue(vehicleFrameNumberString);
                    databaseReference.child("Users").child(userUID).child("isBikeCreated").setValue("true");

                    Toast.makeText(CreateBikeActivity.this, "Bike Created", Toast.LENGTH_SHORT).show();
                    Intent intentNew = new Intent();
                    setResult(RESULT_OK, intentNew);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

}