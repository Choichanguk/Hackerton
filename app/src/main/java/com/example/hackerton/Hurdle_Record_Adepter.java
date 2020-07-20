package com.example.hackerton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hurdle_Record_Adepter extends RecyclerView.Adapter<Hurdle_Record_Adepter.ViewHolder> {
    ArrayList<Hurdle_Record_Box> items = new ArrayList<Hurdle_Record_Box>();
//    OnScoreItemClickListener listener;

    public void addItem(Hurdle_Record_Box item) {
        items.add(item);
    }

    public void setItems(ArrayList<Hurdle_Record_Box> items) {
        this.items = items;
    }

    public Hurdle_Record_Box getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Hurdle_Record_Box item) {
        items.set(position, item);
    }

//    public void setOnItemClickListener(OnScoreItemClickListener listener) {
//        this.listener = listener;
//    }


    @NonNull
    @Override
    public Hurdle_Record_Adepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull Hurdle_Record_Adepter.ViewHolder holder, int position) {
        Hurdle_Record_Box item = items.get(position);
        item.setRanking(position+1);
        holder.rankNo.setText(""+(position+1));
        holder.rankId.setText(item.getId());
        holder.rankRecord.setText(item.getScore());
        holder.setItem(item);

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
        public void setItem(Hurdle_Record_Box item) {
            //rankNo.setText(); //랭킹 순위
            //rankId.setText(); //랭킹 ID
            //rankRecord.setText(); //랭킹 기록


        }
    }
}
