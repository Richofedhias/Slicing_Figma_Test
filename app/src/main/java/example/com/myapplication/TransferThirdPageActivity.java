package example.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class TransferThirdPageActivity extends AppCompatActivity {
private Button btn_lanjut;
private EditText nominal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_third_page);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        nominal = findViewById(R.id.editText4);
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransferThirdPageActivity.this,TransactionSuccessActivity.class));
            }
        });

        nominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Metode ini dipanggil sebelum teks berubah.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Metode ini dipanggil saat teks berubah.
                // Di sini Anda akan mengatur format nominal Rupiah.
                String input = charSequence.toString();

                // Hapus tanda titik dan koma jika ada
                input = input.replace(".", "").replace(",", "");

                // Konversi ke format mata uang Rupiah
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String formattedInput = decimalFormat.format(Long.parseLong(input));

                // Atur teks yang diformat kembali ke EditText
                nominal.removeTextChangedListener(this);
                nominal.setText(formattedInput);
                nominal.setSelection(formattedInput.length());
                nominal.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Metode ini dipanggil setelah teks berubah.
            }
        });
    }
}