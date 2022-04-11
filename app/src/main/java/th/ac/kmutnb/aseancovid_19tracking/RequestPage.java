package th.ac.kmutnb.aseancovid_19tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestPage extends AppCompatActivity {
    userData data;
    EditText address;
    EditText description;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        data = (userData) getIntent().getSerializableExtra("data");
        address = findViewById(R.id.RQ_address);
        address.setText(data.getAddress());
        description = findViewById(R.id.RQ_description);

        databaseReference = FirebaseDatabase.getInstance("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();
    }

    //pop to previous page
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSummit(View v){
        final String descriptionString = description.getText().toString();
        final String addressString = address.getText().toString();
        if(descriptionString.isEmpty()||addressString.isEmpty()){
            Toast.makeText(RequestPage.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
        }
        else{
            databaseReference.child("request").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child("request").child(data.getPhoneNumber()).child("description").setValue(descriptionString);
                    databaseReference.child("request").child(data.getPhoneNumber()).child("address").setValue(addressString);
                    databaseReference.child("request").child(data.getPhoneNumber()).child("status").setValue("รอการตรวจสอบ");
                    Toast.makeText(RequestPage.this, "ส่งคำร้องขอเรียบร้อย", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        Toast.makeText(RequestPage.this, "ส่งคำร้องขอเรียบร้อย...", Toast.LENGTH_LONG).show();
    }
}