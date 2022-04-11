package th.ac.kmutnb.aseancovid_19tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckRequestPage extends AppCompatActivity {
    DatabaseReference databaseReference;
    userData data;
    TextView address;
    TextView description;
    TextView status;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        data = (userData) getIntent().getSerializableExtra("data");
        address = findViewById(R.id.CRQ_address);
        description = findViewById(R.id.CRQ_desc);
        status = findViewById(R.id.CRQ_status);
        name = findViewById(R.id.CRQ_name);

        databaseReference = FirebaseDatabase.getInstance("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();

        databaseReference.child("request").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String getDescription = snapshot.child(data.getPhoneNumber()).child("description").getValue(String.class);
                final String getStatus = snapshot.child(data.getPhoneNumber()).child("status").getValue(String.class);
                final String getAddress = snapshot.child(data.getPhoneNumber()).child("address").getValue(String.class);
                description.setText(getDescription);
                address.setText(getAddress);
                status.setText(getStatus);
                name.setText(data.getName());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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



}