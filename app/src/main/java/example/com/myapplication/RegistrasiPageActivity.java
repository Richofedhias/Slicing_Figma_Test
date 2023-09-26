package example.com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrasiPageActivity extends AppCompatActivity {
    private TextInputLayout eT_username,eT_fullname,eT_email,eT_password,eT_conf_password;
    private TextView tV_login;
    private Button btn_register;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseFirestore fStore;
    private ProgressDialog mLoading;
    String username,fullname,email,password,conf_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_page);
        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        mLoading = new ProgressDialog(this);
        mLoading.setMessage("Please Wait..");
        tV_login = findViewById(R.id.tV_login);
        btn_register = findViewById(R.id.btn_signUp);
        eT_fullname = findViewById(R.id.textInputLayout_fullname);
        eT_username = findViewById(R.id.textInputLayout_username);
        eT_email = findViewById(R.id.textInputLayout_email);
        eT_password = findViewById(R.id.textInputLayout_password);
        eT_conf_password = findViewById(R.id.textInputLayout_conf_password);
        // Pattern regex untuk hanya huruf dan angka (angka opsional)
        String regex = "^[a-zA-Z0-9]*$";
        String regexfullname = "^[a-zA-Z\\s]*$";
        String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Buat objek Pattern
        Pattern patternPassword = Pattern.compile(regexPassword);
        // Buat objek Pattern
        Pattern patternEmail = Pattern.compile(regexEmail);
        // Buat objek Pattern
        Pattern patternFullname = Pattern.compile(regexfullname);
        // Buat objek Pattern
        Pattern pattern = Pattern.compile(regex);

        // Buat objek Matcher


        tV_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrasiPageActivity.this,LoginPageActivity.class));
                finish();
            }
        });
        fullname = eT_fullname.getEditText().getText().toString();
        username = eT_username.getEditText().getText().toString();
        email = eT_email.getEditText().getText().toString();
        password = eT_password.getEditText().getText().toString();
        conf_password = eT_conf_password.getEditText().getText().toString();

        if (username.isEmpty() && fullname.isEmpty() && email.isEmpty() && password.isEmpty() && conf_password.isEmpty()){
            btn_register.setBackgroundResource(R.drawable.btn_unactive);
        }else {
            btn_register.setBackgroundResource(R.drawable.btn_active);
        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }

            private void register() {
                fullname = eT_fullname.getEditText().getText().toString();
                username = eT_username.getEditText().getText().toString();
                email = eT_email.getEditText().getText().toString();
                password = eT_password.getEditText().getText().toString();
                conf_password = eT_conf_password.getEditText().getText().toString();

                 if (username.isEmpty()) {
                    eT_username.setError("Masukan Username Terlebih Dahulu");
                    eT_username.setFocusable(true);
                }else if (username.length() <3){
                    eT_username.setError("Masukan Username Minimal 3 dan maksimal 12");
                    eT_username.setFocusable(true);
                }else if (username.length() >12){
                     eT_username.setError("Masukan Username maksimal 12");
                     eT_username.setFocusable(true);
                 }
                else if (fullname.isEmpty()) {
                    eT_fullname.setError("Masukan Fullname Terlebih Dahulu");
                    eT_fullname.setFocusable(true);
                } else if (fullname.length() < 3) {
                     eT_fullname.setError("Masukan Fullname Minimal 3");
                     eT_fullname.setFocusable(true);
                 }else if (fullname.length() >50){
                         eT_fullname.setError("Masukan Fullname  maksimal 50");
                         eT_fullname.setFocusable(true);
                 }else if (email.isEmpty()) {
                    eT_email.setError("Masukan Email Anda");
                    eT_email.setFocusable(true);
                } else if (password.isEmpty()) {
                    eT_password.setError("Masukan Password Anda");
                    eT_password.setFocusable(true);
                } else if (password.length() < 8) {
                    eT_password.setError("Masukan Password minimal 8");
                    eT_password.setFocusable(true);
                } else if (conf_password.isEmpty()) {
                    eT_conf_password.setError("Masukan Confirm Password Terlebih Dahulu");
                    eT_conf_password.setFocusable(true);
                } else if (!conf_password.equals(password)) {
                    eT_conf_password.setError("Masukan Password yang Sama");
                    eT_conf_password.setFocusable(true);
                } else {
                     Matcher matcher = pattern.matcher(username);
                     if (matcher.matches()) {
                         // Input valid

                     } else {
                         // Input tidak valid
                         Toast.makeText(RegistrasiPageActivity.this, "Inputan hanya berupa Huruf dan angka", Toast.LENGTH_SHORT).show();
                     }

                     Matcher matcherFullname = patternFullname.matcher(fullname);
                     if (matcherFullname.matches()){

                     }else{
                         Toast.makeText(RegistrasiPageActivity.this, "Full name hanya dapat diisi dengan text", Toast.LENGTH_SHORT).show();
                     }

                     Matcher matcherEmail = patternEmail.matcher(email);
                     if (matcherEmail.matches()){

                     }else {

                         Toast.makeText(RegistrasiPageActivity.this, "Pastikan format email anda sudah benar", Toast.LENGTH_SHORT).show();
                     }

                     Matcher matcherPassword = patternPassword.matcher(password);

                     if (matcherPassword.matches()){

                     }else{
                         Toast.makeText(RegistrasiPageActivity.this, "Pastikan membuat password yang aman", Toast.LENGTH_SHORT).show();
                     }
                    registrasiUser(email, password);
                }
            }
        });
    }
    private void registrasiUser(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (task.isSuccessful()) {
                    mLoading.dismiss();
                    String uid = user.getUid();
                    DocumentReference documentReference = fStore.collection("user").document(uid);
                    Toast.makeText(RegistrasiPageActivity.this, "Berhasil Daftar Dengan Email\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    HashMap hashMap = new HashMap();
                    String emailmap = user.getEmail();


                    hashMap.put("email", emailmap);
                    hashMap.put("uid", uid);
                    hashMap.put("nama", fullname);
                    hashMap.put("username", username);

                    documentReference.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RegistrasiPageActivity.this, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrasiPageActivity.this, LoginPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}