package com.example.guanghuili.procrastinationx;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.guanghuili.procrastinationx.Events.Event;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List <Event> eventList;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Event event = eventList.get(position);

        viewHolder.tvName_overview.setText(event.getName());
        viewHolder.tvType_overview.setText(event.getType());
        viewHolder.tvDate_overview.setText(event.getDate());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName_overview;
        public TextView tvType_overview;
        public TextView tvDate_overview;
        public Button btnEdit_overview;
        public Button btnDelete_overview;
        public int id;

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);

            context = ctx;

            tvName_overview = (TextView)view.findViewById(R.id.tvNameID_overview);
            tvType_overview = (TextView)view.findViewById(R.id.tvTypeID_overview);
            tvDate_overview =  (TextView)view.findViewById(R.id.tvDateID_overview);
            btnEdit_overview = (Button)view.findViewById(R.id.btnEditID_overview);
            btnDelete_overview = (Button)view.findViewById(R.id.btnDeleteID_overview);

        }

    }
}
