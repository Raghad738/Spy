package raghad12_1_2016.spy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Settings extends AppCompatActivity
{
    private Button Sms;
    private Button Calls;
    private Button Gbs;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

}
