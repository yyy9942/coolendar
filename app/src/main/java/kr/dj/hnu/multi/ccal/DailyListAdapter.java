package kr.dj.hnu.multi.ccal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class DailyListAdapter extends ArrayAdapter {
    Calendar calendar;
    List<Event> events;
    LayoutInflater inflater;
    TextView daily_event_title;
    TextView daily_event_content;


    public DailyListAdapter(@NonNull Context context, Calendar calendar, List<Event> events) {
        super(context, R.layout.daily_calendar_layout);
        this.calendar = calendar;
        this.events = events;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.daily_cell_layout, null);
        daily_event_title = view.findViewById(R.id.daily_event_title);
        daily_event_content = view.findViewById(R.id.daily_event_content);
        Event event = events.get(position);
        daily_event_title.setText(event.getTitle()+"");
        daily_event_content.setText(event.getContent()+"");

        return view;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return events.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return events.get(position);
    }


}
