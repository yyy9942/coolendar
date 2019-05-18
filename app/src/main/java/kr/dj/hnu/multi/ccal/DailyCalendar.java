package kr.dj.hnu.multi.ccal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class DailyCalendar extends Fragment {
    TextView daily_textview;
    Calendar calendar;
    ImageButton btn_privious;
    ImageButton btn_next;
    List<Event> events;
    DBOpenHelper dbOpenHelper;
    ListView daily_listView;
    DailyListAdapter dailyListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_calendar_layout, container, false);
        daily_textview = view.findViewById(R.id.daily_textview);
        calendar = Calendar.getInstance();
        btn_privious = view.findViewById(R.id.btn_daily_previous);
        btn_next = view.findViewById(R.id.btn_daily_next);
        dbOpenHelper = new DBOpenHelper(getContext());
        daily_listView = view.findViewById(R.id.daily_listview);


        setCalendar();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                setCalendar();
            }
        });

        btn_privious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                setCalendar();
            }
        });


        return view;
    }

    public void setCalendar(){
        daily_textview.setText((calendar.get(Calendar.MONTH)+1) + "월 " + calendar.get(Calendar.DAY_OF_MONTH) + "일");
        events = dbOpenHelper.getEvents(calendar);
        dailyListAdapter = new DailyListAdapter(getContext(), calendar, events);
        daily_listView.setAdapter(dailyListAdapter);


    }
}
