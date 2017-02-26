package raghad12_1_2016.spy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRePassword;
    private EditText etPhone;
    private Button btnNext;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etRePassword=(EditText)findViewById(R.id.etRePassword);
        etPhone=(EditText)findViewById(R.id.etPhone);
        btnNext=(Button) findViewById(R.id.btnNext);
        auth=FirebaseAuth.getInstance();

        eventHandler();

    }
    private void dataHandler()
    {
        String stEmail=etEmail.getText().toString();
        String stPassword=etPassword.getText().toString();
        String stRePassword=etRePassword.getText().toString();
        String stPhone=etPhone.getText().toString();
        boolean isok=true;
        if (stEmail.length()==0){
            etEmail.setError("wrong Email");
            isok=false;
        }
        if (stPassword.length()==0){
            etPassword.setError("Wrong Password");
            isok=false;
        }
        if (stRePassword.length()==0){
            etRePassword.setError("wrong Password");
        isok=false;
        }
        if (stPhone.length()==0){
            etPhone.setError("Wrong Phone");
        }
        if (isok);
        creatAcount(stEmail,stPassword);

    }
    private void eventHandler(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();

            }
        });
    }
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {

        @Override

        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            //4.

            FirebaseUser user=firebaseAuth.getCurrentUser();

            if(user!=null)

            {

                //user is signed in

                Toast.makeText(SignUp.this, "user is signed in.", Toast.LENGTH_SHORT).show();


            }

            else

            {

                //user signed out

                Toast.makeText(SignUp.this, "user signed out.", Toast.LENGTH_SHORT).show();


            }

        }

    };
    @Override

    protected void onStart() {

        super.onStart();

        auth.addAuthStateListener(authStateListener);

    }

    @Override

    protected void onStop() {

        super.onStop();

        if(authStateListener!=null)

            auth.removeAuthStateListener(authStateListener);

    }
    private void creatAcount(String email, String passw) {

        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())

                {

                    Toast.makeText(SignUp.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();

                    //updateUserProfile(task.getResult().getUser());

                    finish();

                }

                else

                {
                    Toast.makeText(SignUp.this, "Authentication failed."+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                    task.getException().printStackTrace();
                }
            }
        });
    }

    }
