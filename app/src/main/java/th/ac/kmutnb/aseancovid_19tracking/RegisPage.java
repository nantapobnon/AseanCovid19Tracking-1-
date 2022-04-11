package th.ac.kmutnb.aseancovid_19tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisPage extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    userData data;

//    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
//            .getReferenceFromUrl("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        data = new userData();
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void enterRegis(View v){
        EditText name = findViewById(R.id.nameInput);
        EditText password = findViewById(R.id.passwordInput);
        EditText cfPassword = findViewById(R.id.cfPasswordInput);
        EditText email = findViewById(R.id.emailInput);
        EditText phoneNumber = findViewById(R.id.phoneInput);
        EditText age = findViewById(R.id.ageInput);
        EditText address = findViewById(R.id.addressInput);
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);


        data.setSex((String) radioButton.getText());
        data.setName(name.getText().toString());
        data.setPassword(password.getText().toString());
        data.setEmail(email.getText().toString());
        data.setAge(Integer.parseInt(age.getText().toString()));
        data.setPhoneNumber(phoneNumber.getText().toString());
        data.setAddress(address.getText().toString());

        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(data.getPhoneNumber())){
                    Toast.makeText(RegisPage.this, "มีเบอร์นี้ในระบบเเล้ว", Toast.LENGTH_LONG).show();
                }
                else{
                    databaseReference.child("user").child(data.getPhoneNumber()).child("fullname").setValue(data.getName());
                    databaseReference.child("user").child(data.getPhoneNumber()).child("password").setValue(data.getPassword());
                    databaseReference.child("user").child(data.getPhoneNumber()).child("age").setValue(data.getAge());
                    databaseReference.child("user").child(data.getPhoneNumber()).child("email").setValue(data.getEmail());
                    databaseReference.child("user").child(data.getPhoneNumber()).child("address").setValue(data.getAddress());
                    databaseReference.child("user").child(data.getPhoneNumber()).child("sex").setValue(data.getSex());
                    Toast.makeText(RegisPage.this, "ลงทะเบียนเสร็จสิ้น", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}