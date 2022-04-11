package th.ac.kmutnb.aseancovid_19tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserMainPage extends AppCompatActivity {
    userData data;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        data = (userData) getIntent().getSerializableExtra("data");
        TextView name = findViewById(R.id.nameTxt);
        name.setText(data.getName());

        databaseReference = FirebaseDatabase.getInstance("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();
    }

    public void checkRequest(View v){
        databaseReference.child("request").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(LoginPage.this, "เชื่อมดาต้าเบส", Toast.LENGTH_LONG).show();
                if(!snapshot.hasChild(data.getPhoneNumber())){
                    openRequestPage();
                }
                else{
                    openCheckRequestPage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openCheckRequestPage(){
        Intent itn = new Intent(this, CheckRequestPage.class);
        itn.putExtra("data", data);
        startActivity(itn);
    }

    public void openRequestPage(){
        Intent itn = new Intent(this, RequestPage.class);
        itn.putExtra("data", data);
        startActivity(itn);
    }
}