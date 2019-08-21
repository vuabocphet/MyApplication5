package com.developer.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class HinhNenAdapter  extends RecyclerView.Adapter<HinhNenAdapter.ViewHodelA> {


    private Context context;
    private List<String> list;

    public HinhNenAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodelA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodelA(LayoutInflater.from(parent.getContext()).inflate(R.layout.iyem_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelA holder, int position) {

        Picasso.get().load(list.get(position)).fit().transform(boderIMG(0, 5))
                .centerCrop().error(R.drawable.ic_launcher_background).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                //code loading

            }

            @Override
            public void onError(Exception e) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodelA extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHodelA(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);

        }
    }

    private Transformation boderIMG(int boderW, int boderConer) {
        return new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(boderW)
                .cornerRadiusDp(boderConer)
                .oval(false)
                .build();

    }
}
