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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends AppCompatActivity {
    private TextInputLayout email,pass;
    private TextView daftar, lupa_pass;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoading;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.textInputLayout_email);
        pass = findViewById(R.id.textInputLayout_password);
        login = findViewById(R.id.btn_login);
        daftar = findViewById(R.id.tV_signUp);
        lupa_pass = findViewById(R.id.tV_forget_password);
        mLoading = new ProgressDialog(this);
        mLoading.setMessage("Please Wait..");
        mAuth = FirebaseAuth.getInstance();
        String email_user = email.getEditText().getText().toString();
        final String password_user = pass.getEditText().getText().toString();
        if (email_user.isEmpty() && password_user.isEmpty()){
            login.setBackgroundResource(R.drawable.btn_unactive);
        }else{
            login.setBackgroundResource(R.drawable.btn_active);
        }


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, RegistrasiPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        lupa_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, LupaPasswordPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_user = email.getEditText().getText().toString();
                final String password_user = pass.getEditText().getText().toString();
               
                if (email_user.isEmpty()) {
                    email.setError("Masukan Email");
                    email.setFocusable(true);
                } else if (password_user.isEmpty()) {
                    pass.setError("Masukan Password");
                    pass.setFocusable(true);
//                } else if (!email_user.matches(emailPattern)) {
//                    email.setError("Masukan Email yang Valid");
//                    email.setFocusable(true);
                } else {
                    login(email_user, password_user);
                }
            }
        });
    }

    private void login(String emailUser, String passwordUser) {
        mLoading.show();

        mAuth.signInWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginPageActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPageActivity.this, DashboardPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mLoading.dismiss();
                Toast.makeText(LoginPageActivity.this, "Gagal Login" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}