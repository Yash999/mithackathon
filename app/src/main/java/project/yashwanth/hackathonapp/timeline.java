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
        t.date="Time: 07AM -- 16-10-2018";
        t.details="Details: place";
        t.level="Level: medium";
        t.vnumber="Number of vehicle: aprox 70";
        timelinedataset t1=new timelinedataset();
        t1.date="Time: 01PM -- 16-10-2018";
        t1.details="Details: place";
        t1.level="Level: High";
        t1.vnumber="Number of vehicle: aprox 120";
        datatoinsert.add(t1);
        datatoinsert.add(t);
        mTimelineRecyclerViewAdapter.notifyDataSetChanged();
    }
}
