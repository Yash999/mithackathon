package project.yashwanth.hackathonapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class timeline extends AppCompatActivity {


    private Context mContext;
    private RecyclerView mRecyclerView;
    TimelineRecyclerViewAdapter mTimelineRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final List<timelinedataset> datatoinsert = new ArrayList<>();
    public static final String DATE_TIME_FORMAT = "kk:mm:ss dd-MM-yyyy ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        mContext = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_timeline);
        mTimelineRecyclerViewAdapter = new TimelineRecyclerViewAdapter(mContext, datatoinsert);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setAdapter(mTimelineRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        datatoinsert.clear();
        timelinedataset t=new timelinedataset();
        t.date="12-10-2018";
        t.details="place";
        t.level="medium";
        t.vnumber="aprox 70";
        datatoinsert.add(t);
        mTimelineRecyclerViewAdapter.notifyDataSetChanged();
    }
}
