package com.example.bai_ktck_2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class List_adapter extends FirebaseRecyclerAdapter<List_model,List_adapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public List_adapter(@NonNull FirebaseRecyclerOptions<List_model> options) {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull List_model model) {
        holder.txt_tenkh.setText(model.getTenkh());
        holder.txt_tentg.setText(model.getTentg());
        holder.txt_mausac.setText(model.getMausac());
        holder.txt_dactinh.setText(model.getDactinh());
        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .into(holder.img);
        ///xoa 1 item trong recyclerview
        holder.rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txt_tenkh.getContext());
                builder.setTitle("Bạn có muốn xóa không?");
                builder.setMessage("Dữ liệu xóa, không thể khôi phục!");
                // xoa
                builder.setPositiveButton("Xóa ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Tree")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txt_tenkh.getContext(),"Xóa không thành công! ",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

        holder.list_model = model;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView txt_tenkh,txt_tentg,txt_mausac,txt_dactinh;
        ImageView img,rm;

        List_model list_model;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);
            rm = (ImageView) itemView.findViewById(R.id.rm);

            txt_tenkh=(TextView) itemView.findViewById(R.id.txt_tenkh);
            txt_tentg=(TextView) itemView.findViewById(R.id.txt_tentg);
            txt_mausac=(TextView) itemView.findViewById(R.id.txt_mausac);
            txt_dactinh=(TextView) itemView.findViewById(R.id.txt_dactinh);

//click mo ta
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), Mota_Fish.class);
                    intent.putExtra("list_model",list_model);

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }


}
