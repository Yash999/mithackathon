package project.yashwanth.hackathonapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.PERecyclerViewHolder> {

    private LayoutInflater mLayoutInflater;
    List<timelinedataset> datatoinsert = Collections.emptyList();
    private Context mContext;


    public TimelineRecyclerViewAdapter(Context ctx, List<timelinedataset> list) {
        this.mContext = ctx;
        this.datatoinsert = list;
        mLayoutInflater=LayoutInflater.from(ctx);

    }
    @NonNull
    @Override
    public PERecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.template_timeline, viewGroup, false);
        PERecyclerViewHolder holder = new PERecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PERecyclerViewHolder holder, int position) {
        holder.tv1.setText(datatoinsert.get(position).date);
        holder.tv2.setText(datatoinsert.get(position).details);
        holder.tv3.setText(datatoinsert.get(position).level);
        holder.tv4.setText(datatoinsert.get(position).vnumber);
    }

    @Override
    public int getItemCount() {
        return datatoinsert.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

        public class PERecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4;
        LinearLayout pe_ll;
        public PERecyclerViewHolder(final View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.tv_time);
            tv2=(TextView)itemView.findViewById(R.id.tv_details);
            tv3=(TextView)itemView.findViewById(R.id.tv_level);
            tv4=(TextView)itemView.findViewById(R.id.tv_v);
        }
    }
}
