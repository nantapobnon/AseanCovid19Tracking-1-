package th.ac.kmutnb.aseancovid_19tracking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void toDataAll(View v){
        Intent itn = new Intent(this, DataAll.class);
        startActivity(itn);
    }
    public void toRegis(View v){
        Intent itn = new Intent(this, RegisPage.class);
        startActivity(itn);
    }
    public void toLogin(View v){
        Intent itn = new Intent(this, LoginPage.class);
        startActivity(itn);
    }
}