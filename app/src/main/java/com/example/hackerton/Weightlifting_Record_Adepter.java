package com.example.hackerton;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Weightlifting_Record_Adepter extends RecyclerView.Adapter<Weightlifting_Record_Adepter.ViewHolder> {
    ArrayList<Weightlifting_Record_Box> items = new ArrayList<Weightlifting_Record_Box>();
//    OnScoreItemClickListener listener;
    String user_id;
    public Weightlifting_Record_Adepter(String user_id){
        this.user_id = user_id;
    }

    public void addItem(Weightlifting_Record_Box item) {
        items.add(item);
    }

    public void setItems(ArrayList<Weightlifting_Record_Box> items) {
        this.items = items;
    }

    public Weightlifting_Record_Box getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Weightlifting_Record_Box item) {
        items.set(position, item);
    }




    @NonNull
    @Override
    public Weightlifting_Record_Adepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.rank_list_cardview, parent, false);

        /*Button remove;
        remove = itemView.findViewById(R.id.removeScore);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(getItemCount());
                notifyItemRemoved(getItemCount());
                notifyItemRangeChanged(getItemCount(), items.size());
            }
        });*/

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Weightlifting_Record_Adepter.ViewHolder holder, int position) {
        Weightlifting_Record_Box item = items.get(position);
        holder.rankNo.setText(""+(position+1));
        holder.rankId.setText(item.getId());
        holder.rankRecord.setText(item.getScore());
        holder.setItem(item);

        if(user_id.equals(item.getId())){
            Log.e("adapter: ", user_id);
            Log.e("adapter: ", "user_id");
          /*  holder.itemView.setBackgroundColor(Color.YELLOW);*/
            holder.rankNo.setTextColor(Color.GREEN);
            holder.rankId.setTextColor(Color.GREEN);
            holder.rankRecord.setTextColor(Color.GREEN);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }







    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rankNo;
        TextView rankId;
        TextView rankRecord;
        Button remove;



        public ViewHolder(final View itemView) {
            super(itemView);





            rankNo = itemView.findViewById(R.id.txtRankNo);
            rankId = itemView.findViewById(R.id.txtId);
            rankRecord = itemView.findViewById(R.id.txtrecord);



        }


        // DB연결 후 작성 필요
        public void setItem(Weightlifting_Record_Box item) {
            //rankNo.setText(); //랭킹 순위
            //rankId.setText(); //랭킹 ID
            //rankRecord.setText(); //랭킹 기록


        }
    }
}
