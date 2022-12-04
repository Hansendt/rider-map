package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    private ImageButton btnBack;
    private EditText inEmail, inPass;
    private TextView goSignup;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        btnBack = findViewById(R.id.btnBack);
        inEmail = findViewById(R.id.inEmail);
        inPass = findViewById(R.id.inPass);
        goSignup = findViewById(R.id.goSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backWelcome = new Intent(Login.this, Welcome.class);
                startActivity(backWelcome);
            }
        });

        goSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inSignup = new Intent(Login.this, Signup.class);
                startActivity(inSignup);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inEmail.getText().toString();
                final String pass = inPass.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(email).exists()) {
                                final String getPass = snapshot.child(email).child("Password").getValue(String.class);
                                if(getPass.equals(pass)){
                                    Intent inHome = new Intent(Login.this, Home.class);
                                    inHome.putExtra("email", email);
                                    startActivity(inHome);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Login.this, "Email not found", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}