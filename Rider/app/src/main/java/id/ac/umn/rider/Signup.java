package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Signup extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://rider-6018c-default-rtdb.firebaseio.com/");

    private ImageButton btnBacktoWelcome;
    private Button btnSignupConfirm;
    EditText signFname, signLname, signEmail, signEmailConfirm, signPass, signPassConfirm;
    private TextView goLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        signFname = findViewById(R.id.signFname);
        signLname = findViewById(R.id.signLname);
        signEmail = findViewById(R.id.signEmail);
        signEmailConfirm = findViewById(R.id.signEmailConfirm);
        signPass = findViewById(R.id.signPass);
        signPassConfirm = findViewById(R.id.signPassConfirm);

        btnBacktoWelcome = findViewById(R.id.btnBacktoWelcome);
        btnSignupConfirm = findViewById(R.id.btnSignupConfirm);
        goLogin = findViewById(R.id.goLogin);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Signup.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("We are creating your account");
        progressDialog.setCancelable(false);

        profilePicture = findViewById(R.id.profilePicture);


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
                final String emailConfirm = signEmailConfirm.getText().toString();
                final String pass = signPass.getText().toString();
                final String passConfirm = signPassConfirm.getText().toString();

                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || emailConfirm.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (!email.equals(emailConfirm)){
                    Toast.makeText(Signup.this, "Email doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(passConfirm)){
                    Toast.makeText(Signup.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUp(fname, lname, email, pass);
//                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.child(username).exists()){
//                                Toast.makeText(Signup.this, "Email already exists", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                //send data to firebase
//                                databaseReference.child("Users").child(username).child("First Name").setValue(fname);
//                                databaseReference.child("Users").child(username).child("Last Name").setValue(lname);
//                                databaseReference.child("Users").child(username).child("Email").setValue(email);
//                                databaseReference.child("Users").child(username).child("Password").setValue(pass);
//                                databaseReference.child("Users").child(username).child("isBikeCreated").setValue("false");
//
//                                Toast.makeText(Signup.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });

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

    public void newProfilePicture(View view) {
        com.github.dhaval2404.imagepicker.ImagePicker.with(Signup.this)
                .crop()	//User can only select image from Gallery
                .start();
    }

    private void signUp(String fname, String lname, String email, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() != null){
                    FirebaseUser user = task.getResult().getUser();
                    if (user != null){
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fname + " " + lname)
                                .build();

                        user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    } else{
                        Toast.makeText(Signup.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }


                    String uid = user.getUid();
                    databaseReference.child("Users").child(uid).child("isBikeCreated").setValue("false");
                    Toast.makeText(Signup.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), Welcome_2.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}