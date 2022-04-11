package th.ac.kmutnb.aseancovid_19tracking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OneData extends AppCompatActivity {
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_data);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        data = (Data) getIntent().getSerializableExtra("data");



        TextView tv1 = (findViewById(R.id.txtCountry));
        TextView tv2 = (findViewById(R.id.txtInfect));
        ImageView iv = (findViewById(R.id.imgCountry));
        TextView todayInfected = findViewById(R.id.txtTodayInfect);
        TextView death = findViewById(R.id.txtDeath);
        TextView todayDeath = findViewById(R.id.txtTodayDeath);
        TextView recovered = findViewById(R.id.txtRecov);
        TextView todayRecovered = findViewById(R.id.txtTodayRecov);

        tv1.setText(data.getmText1());
        tv2.setText("จำนวนผู้ติดเชื้อทั้งหมด : "+String.valueOf(data.getmNum()));
        iv.setImageResource(data.getmIcon());
        todayInfected.setText("จำนวนผู้ติดเชื้อในวันนี้ : "+data.getTodayInfected());
        death.setText("จำนวนผู้เสียชีวิตทั้งหมด : "+data.getDeath());
        todayDeath.setText("จำนวนผู้เสียชีวิตในวันนี้ : "+data.getTodayDeath());
        recovered.setText("จำนวนผู้ที่รักษาหายทั้งหมด : "+data.getRecovered());
        todayRecovered.setText("จำนวนผู้ที่รักษาหายเเล้วในวันนี้ : "+data.getTodayRecovered());

        int textSize = 20;
        tv2.setTextSize(textSize);
        todayInfected.setTextSize(textSize);
        death.setTextSize(textSize);
        todayDeath.setTextSize(textSize);
        recovered.setTextSize(textSize);
        todayRecovered.setTextSize(textSize);
    }
}