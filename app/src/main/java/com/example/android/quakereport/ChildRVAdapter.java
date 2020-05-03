package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChildRVAdapter extends RecyclerView.Adapter<ChildRVAdapter.ChildViewHolder> {

    private Context context;
    private ArrayList<ChildObject> childList;

    public ChildRVAdapter(Context context, ArrayList<ChildObject> childList){
        this.context = context;
        this.childList = childList;
    }

    @NonNull
    @Override
    public ChildRVAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup Child, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(Child.getContext());
        View view= layoutInflater.inflate(R.layout.child_item_layout, Child, false);
        ChildViewHolder viewHolder = new ChildViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRVAdapter.ChildViewHolder holder, int position) {
        holder.title.setText(childList.get(position).getTitle());
        holder.icon.setImageResource(R.drawable.dog);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView icon;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            icon = itemView.findViewById(R.id.icon_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EarthquakeActivity.class);
                    intent.putExtra("PARAMETER_URL", childList.get(getAdapterPosition()).getTitle());
                    context.startActivity(intent);
                }
            });
        }
    }
}
