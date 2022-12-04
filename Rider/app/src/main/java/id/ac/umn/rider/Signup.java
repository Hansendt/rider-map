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

public class Signup extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    private ImageButton btnBacktoWelcome;
    private Button btnSignupConfirm;
    EditText signFname, signLname, signEmail, signUsername, signPass, signPassConfirm;
    private TextView goLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        signFname = findViewById(R.id.signFname);
        signLname = findViewById(R.id.signLname);
        signEmail = findViewById(R.id.signEmail);
        signUsername = findViewById(R.id.signUsername);
        signPass = findViewById(R.id.signPass);
        signPassConfirm = findViewById(R.id.signPassConfirm);

        btnBacktoWelcome = findViewById(R.id.btnBacktoWelcome);
        btnSignupConfirm = findViewById(R.id.btnSignupConfirm);
        goLogin = findViewById(R.id.goLogin);


        btnBacktoWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backWelcome2 = new Intent(Signup.this, Welcome.class);
                startActivity(backWelcome2);
            }
        });

        btnSignupConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fname = signFname.getText().toString();
                final String lname = signLname.getText().toString();
                final String email = signEmail.getText().toString();
                final String username = signUsername.getText().toString();
                final String pass = signPass.getText().toString();
                final String passConfirm = signPassConfirm.getText().toString();

                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || username.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(passConfirm)){
                    Toast.makeText(Signup.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(username).exists()){
                                Toast.makeText(Signup.this, "Email already exists", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //send data to firebase
                                databaseReference.child("Users").child(username).child("First Name").setValue(fname);
                                databaseReference.child("Users").child(username).child("Last Name").setValue(lname);
                                databaseReference.child("Users").child(username).child("Email").setValue(email);
                                databaseReference.child("Users").child(username).child("Password").setValue(pass);
                                databaseReference.child("Users").child(username).child("isBikeCreated").setValue("false");

                                Toast.makeText(Signup.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inLogin = new Intent(Signup.this, Login.class);
                startActivity(inLogin);
            }
        });


    }
}