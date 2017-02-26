package raghad12_1_2016.spy;

import android.content.Intent;
import android.opengl.EGLSurface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import raghad12_1_2016.spy.data.MyAdapter;
import raghad12_1_2016.spy.data.Mytt;

public class PeopleList extends AppCompatActivity
{
    private ImageButton ibPlus1;
    private ListView lvTasks;
    private MyAdapter adapterTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);


        ibPlus1 = (ImageButton) findViewById(R.id.ibPlus1);
        lvTasks = (ListView) findViewById(R.id.lvtasks);
        adapterTask=new MyAdapter(this,R.layout.item);
        lvTasks.setAdapter(adapterTask);
        initListView();

//initListView();

        eventHandlrer();


    }

    private void  eventHandlrer()
    {
        ibPlus1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(PeopleList.this, AddNumbers.class);
                startActivity(i);



            }
        });
    }
    protected void onStart(){
        super.onStart();




    }
    private void initListView(){
        String email=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference(email);
        reference.child("raghad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterTask.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Mytt mytt = ds.getValue(Mytt.class);
                    mytt.setId(ds.getKey());
                    adapterTask.add(mytt);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



}
    }
