package raghad12_1_2016.spy.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import raghad12_1_2016.spy.R;

/**
 * Created by user on 11/27/2016.
 */
public class MyAdapter extends ArrayAdapter<Mytt>//
{
    private DatabaseReference reference;

    public MyAdapter(Context context, int resource)
    {
        super(context, resource);
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
        reference= FirebaseDatabase.getInstance().getReference(email).child("raghad");

    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);

        TextView tvPhone=(TextView)convertView.findViewById(R.id.tvPhone);
        TextView tvName=(TextView) convertView.findViewById(R.id.tvName);
        ImageButton btnDel=(ImageButton) convertView.findViewById(R.id.btnDel);

        final Mytt mytt=getItem(position);
        tvPhone.setText(mytt.getPhone());
        tvName.setText(mytt.getTitle());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(mytt.getId()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError==null)
                        {
                            Toast.makeText(getContext(),"Delete d!",Toast.LENGTH_LONG).show();
                            remove(mytt);
                            setNotifyOnChange(true);
                        }
                    }
                });


            }
        });
        return convertView;



    }



}
