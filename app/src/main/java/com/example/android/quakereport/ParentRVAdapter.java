package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ParentRVAdapter extends RecyclerView.Adapter<ParentRVAdapter.ParentViewHolder> {

    private Context context;
    private ArrayList<ParentObject> parentList;

    public ParentRVAdapter(Context context, ArrayList<ParentObject> parentList){
        this.context = context;
        this.parentList = parentList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.parent_item_layout, parent, false);
        ParentViewHolder viewHolder = new ParentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParentRVAdapter.ParentViewHolder holder, int position) {
        holder.title.setText(parentList.get(position).getTitle());

        ChildRVAdapter childRVAdapter = new ChildRVAdapter(context, parentList.get(position).getChildList());
        holder.childrv.setHasFixedSize(false);
        holder.childrv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.childrv.setAdapter(childRVAdapter);
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView childrv;
        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            childrv = itemView.findViewById(R.id.child_rv);
        }
    }
}
