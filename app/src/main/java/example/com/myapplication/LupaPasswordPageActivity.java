package example.com.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordPageActivity extends AppCompatActivity {
    private EditText email;
    private Button submit;
    ProgressDialog mLoading;
    FirebaseAuth firebaseAuth;
    private LinearLayout kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password_page);
        email = findViewById(R.id.eT_email);
        submit = findViewById(R.id.btn_kirim);
        kembali = findViewById(R.id.linearLayout);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LupaPasswordPageActivity.this, LoginPageActivity.class));
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoading.show();
                String email_user = email.getText().toString().trim();
                forgetPassword(email_user);
            }
        });
    }

    private void forgetPassword(String email_user) {
        mLoading.setMessage("Please Wait..");
        mLoading.show();
        firebaseAuth.sendPasswordResetEmail(email_user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mLoading.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LupaPasswordPageActivity.this, "Email Sending..", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LupaPasswordPageActivity.this, LoginPageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LupaPasswordPageActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mLoading.dismiss();
                        Toast.makeText(LupaPasswordPageActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}