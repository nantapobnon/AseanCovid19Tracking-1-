package th.ac.kmutnb.aseancovid_19tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance("https://asean-covid-19-tracking-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();

    }

    public void onLogin(View v){
        EditText phoneNumber = findViewById(R.id.inputPhone);
        EditText password = findViewById(R.id.inputPassword);

        final String phoneString = phoneNumber.getText().toString();
        final String passwordString = password.getText().toString();
        if(phoneString.isEmpty()||passwordString.isEmpty()){
            Toast.makeText(LoginPage.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
        }
        else{
            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Toast.makeText(LoginPage.this, "เชื่อมดาต้าเบส", Toast.LENGTH_LONG).show();
                    if(snapshot.hasChild(phoneString)){
                        final String getPassword = snapshot.child(phoneString).child("password").getValue(String.class);
                        if(getPassword.equals(passwordString)){
                            Toast.makeText(LoginPage.this, "เข้าสู่ระบบ", Toast.LENGTH_LONG).show();
                            userData data = new userData();
                            data.setSex(snapshot.child(phoneString).child("sex").getValue(String.class));
                            data.setName(snapshot.child(phoneString).child("fullname").getValue(String.class));
                            data.setEmail(snapshot.child(phoneString).child("email").getValue(String.class));
                            data.setAge(snapshot.child(phoneString).child("age").getValue(Integer.class));
                            data.setPhoneNumber(phoneString);
                            data.setAddress(snapshot.child(phoneString).child("address").getValue(String.class));
                            openUserMainPage(data);

                        }
                        else{
                            Toast.makeText(LoginPage.this, "เบอร์โทรหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginPage.this, "เบอร์โทรหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void openUserMainPage(userData data){
        Intent itn = new Intent(this, UserMainPage.class);
        itn.putExtra("data", data);
        startActivity(itn);
    }
}