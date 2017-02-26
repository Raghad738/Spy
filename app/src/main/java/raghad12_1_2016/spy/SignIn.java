package raghad12_1_2016.spy;

import android.content.Intent;
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

public class SignIn extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnForgotPassword;
    private Button btnLetsSpy;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent i= new Intent(SignIn.this,AddNumbers.class);
            startActivity(i);
            finish();
        }

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnForgotPassword=(Button)findViewById(R.id.btnForgotPassword);
        btnLetsSpy=(Button)findViewById(R.id.btnLetsSpy);
        auth=FirebaseAuth.getInstance();


        eventHandlrer();
    }
    private void dataHandler()
    {
        boolean isok=true;
        String stEmail=etEmail.getText().toString();
        String stPassword=etPassword.getText().toString();

        if (stEmail.length()==0){
            etEmail.setError("Wrong Email");
            isok=false;}
        if (stPassword.length()==0) {
            etPassword.setError("Wrong Password");
            isok = false;}
            if (isok)
                signIn(stPassword,stEmail);


    }

    /**
     * puttind event handler for (listeners)
     */
    private void eventHandlrer()
    {
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                dataHandler();
            }
        });

        btnLetsSpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i= new Intent(SignIn.this, AddNumbers.class);
                startActivity(i);

            }
        });
        dataHandler();
    }
    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())

                {

                    Toast.makeText(SignIn.this, "signIn Successful.", Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(SignIn.this, AddNumbers.class);
                    startActivity(i);
                    // Intent intent=new Intent(LogInActivity.this,MainFCMActivity.class);

                    //   startActivity(intent);

                    //  finish();

                }

                else

                {

                    Toast.makeText(SignIn.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    task.getException().printStackTrace();

                }

            }

        });
    }

}
