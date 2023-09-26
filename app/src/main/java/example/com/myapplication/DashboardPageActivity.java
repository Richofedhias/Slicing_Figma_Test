package example.com.myapplication;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DashboardPageActivity extends AppCompatActivity {
    private LinearLayout ll_transaksi;
    AccountAdapter accountAdapter;
    ArrayList<AccountModel> data = new ArrayList<>();
    private RecyclerView rV_account,rV_transaksi,rV_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);
        ll_transaksi = findViewById(R.id.LL_transaksi);
        rV_activity = findViewById(R.id.rV_activity);
        rV_account = findViewById(R.id.rV_account);
        rV_transaksi = findViewById(R.id.rV_transaksi);



//        List<AccountModel> listModel = new ArrayList<>();
//        listModel.add(new AccountModel("Richo Fedhia S","160299","28.000.000","16 Februari 2025"));
//        listModel.add(new AccountModel("Richo Fedhia S","160299","28.000.000","16 Februari 2025"));
//        listModel.add(new AccountModel("Richo Fedhia S","160299","28.000.000","16 Februari 2025"));
//        listModel.add(new AccountModel("Richo Fedhia S","160299","28.000.000","16 Februari 2025"));
//        listModel.add(new AccountModel("Richo Fedhia S","160299","28.000.000","16 Februari 2025"));
//        rV_account.setLayoutManager(new LinearLayoutManager(this));
//        rV_account.setAdapter(new AccountAdapter(getApplicationContext(),listModel));
        // That's all!



        ll_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardPageActivity.this, TransferPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}