package example.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.myViewHolder> {
    Context context;
    List<AccountModel> list;

    public AccountAdapter(Context context, List<AccountModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AccountAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_account, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.myViewHolder holder, int position) {
            holder.name_account.setText(list.get(position).getName_account());
            holder.number_account.setText(list.get(position).getAccount_number());
            holder.nominal.setText(list.get(position).getNominal());
            holder.expired.setText(list.get(position).getExpired());

//            holder.btn_pindah_dana.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    view.getContext().startActivity(new Intent(view.getContext(),TransferSecondaryPageActivity.class));
//                }
//            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        private TextView name_account,number_account,nominal,expired;
        private Button btn_pindah_dana,btn_qr;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name_account = itemView.findViewById(R.id.tV_name_account);
            number_account = itemView.findViewById(R.id.tV_no_account);
            nominal = itemView.findViewById(R.id.tV_nominal);
            expired = itemView.findViewById(R.id.tV_expired);
            btn_qr = itemView.findViewById(R.id.btn_qr);
            btn_pindah_dana = itemView.findViewById(R.id.btn_pindah_dana);
        }
    }
}
