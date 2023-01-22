package com.example.shortstoryEnApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.VHclass> {
    ArrayList<Item_Parameters> arrdata;
    interfaceItem listener;

    public void setArrdata(ArrayList<Item_Parameters> arrdata) {
        this.arrdata = arrdata;
    }

    public RV_Adapter(ArrayList<Item_Parameters> arrdata, interfaceItem listener) {
        this.arrdata = arrdata;
        this.listener=listener;
    }

    @NonNull
    @Override
    public VHclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.storyinlistlayout,null,false);

        return new VHclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VHclass holder, int position) {
        Item_Parameters story=arrdata.get(position);
       holder.Titel_story.setText(story.getTitel());
       holder.Titel_story.setTag(story.getId());
    }

    @Override
    public int getItemCount() {
        return arrdata.size();
    }

    class VHclass extends RecyclerView.ViewHolder {
        TextView Titel_story;
        public VHclass(@NonNull View itemView) {
            super(itemView);
            Titel_story=itemView.findViewById(R.id.titelstory_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 int storyId= (int) Titel_story.getTag();
                 listener.OnclickItemRV(storyId);
                }
            });
        }
    }
}
