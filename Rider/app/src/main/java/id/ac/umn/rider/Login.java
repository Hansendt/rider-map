package id.ac.umn.rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

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

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("We are logging you in");
        progressDialog.setCancelable(false);

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
                    login(email, pass);
                }
            }
        });
    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() != null){
                    if(task.getResult() != null){
                        reload();
                    }
                    else{
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), Home.class));
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