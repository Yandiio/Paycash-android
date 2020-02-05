package com.nawasena.dev.paycash.Activities.Checkout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paycash.Activities.Checkout.Models.Checkout;
import com.example.paycash.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    public CheckoutAdapter(Context mContext, List<Checkout> checkoutList) {
        this.mContext = mContext;
        this.checkoutList = checkoutList;
    }

    Context mContext;
    List<Checkout> checkoutList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_checkout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Locale localeID = new Locale("in","id");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        Checkout check = checkoutList.get(position);

        holder.tvPrice.setText(formatRupiah.format(Double.valueOf(check.getHarga())));
        holder.tvData.setText(check.getDetail());
        holder.tvDate.setText("\u2002"+dateTime(check.getGetPublisherAt()));

    }

    @Override
    public int getItemCount() {
        return checkoutList.size();
    }

    public String dateTime(String t){
        PrettyTime pretty = new PrettyTime(new Locale(getCountry()));
        String time = null;

        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date dt = date.parse(t);
            time = pretty.format(dt);
        }catch(Exception e){
            e.printStackTrace();
        }

        return time;
    }

    public String getCountry()
    {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvData,tvPrice,tvDate ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvData = itemView.findViewById(R.id.detailOrder);
            tvDate = itemView.findViewById(R.id.tanggalOrder);
            tvPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}
