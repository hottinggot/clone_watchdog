package com.example.sketch2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sketch2.Pojo.Row;
import com.example.sketch2.R;

import java.util.List;

public class DangerAdapter extends RecyclerView.Adapter<DangerAdapter.ViewHolder> {

    private List<Row> dangerList;
    private View.OnClickListener onItemViewClickListener;

    public DangerAdapter(List<Row> dangerList){
        this.dangerList = dangerList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout info_layout;
        TextView food_title, food_company, food_reason;
        ImageView food_img;

        ViewHolder(View itemView){
            super(itemView);
            info_layout = itemView.findViewById(R.id.info_layout);
            food_title = itemView.findViewById(R.id.food_title);
            food_company = itemView.findViewById(R.id.food_company);
            food_img = itemView.findViewById(R.id.food_img);
            food_reason = itemView.findViewById(R.id.food_reason);
        }
    }

    @Override
    public DangerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.object_layout, parent, false);
        if(onItemViewClickListener != null){
            v.setOnClickListener(onItemViewClickListener);
        }
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount(){
        return dangerList.size();
    }

    @Override
    public void onBindViewHolder(final DangerAdapter.ViewHolder holder, final int position){
        try{
            holder.food_title.setText(dangerList.get(position).getPRDTNM());
            holder.food_company.setText(dangerList.get(position).getBSSHNM());
            holder.food_reason.setText(dangerList.get(position).getRTRVLPRVNS());
            //holder.food_img.setImageURI(collectionList.get(position).getIMG_FILE_PATH());
        } catch (Exception e) {System.out.println(e);}
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
