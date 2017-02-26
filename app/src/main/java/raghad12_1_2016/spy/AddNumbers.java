package raghad12_1_2016.spy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import raghad12_1_2016.spy.data.Mytt;

public class AddNumbers extends AppCompatActivity {
    private EditText etNumber1;
    private EditText etName1;
    private ImageButton ibPlus;
    private Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_numbers);

        etNumber1 = (EditText) findViewById(R.id.etNumber1);
        etName1 = (EditText) findViewById(R.id.etName1);
        ibPlus = (ImageButton) findViewById(R.id.ibPlus);
        btnNext = (Button) findViewById(R.id.btnNext);

        eventHandler();
    }

    private void dataHandler() {
        String stNumber1 = etNumber1.getText().toString();
        String stName1 = etName1.getText().toString();
        boolean isok = true;
        if (stNumber1.length() == 0) {
            etNumber1.setError("Wrong Number");
            isok = false;
        }

        if (stName1.length() == 0) {
            etName1.setError("Wrong Name");
            isok = false;
        }

            if (isok) {
                Intent i= new Intent(AddNumbers.this,PeopleList.class);
                startActivity(i);

                Mytt mytt = new Mytt();
                mytt.setTitle(stName1);
                mytt.setPhone(stNumber1);


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                email = email.replace(".", "_");

                //smslist                       //oject MySMS
                reference.child(email).child("raghad").push().setValue(mytt, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null)//addtion successful
                        {
                            Toast.makeText(AddNumbers.this, "Saved", Toast.LENGTH_LONG).show();

                        } else//adding failed
                        {

                            Toast.makeText(AddNumbers.this, "Saving Failed" + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

        }


    private void eventHandler() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Intent i= new Intent(AddNumbers.this, .class);
//                startActivity(i);
            }
        });
        ibPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dataHandler();

            }
        });

    }
}
