package kr.dj.hnu.multi.ccal;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekListAdapter extends ArrayAdapter {
    List<Event> events;
    LayoutInflater inflater;
    Calendar calendar;
    DBOpenHelper dbOpenHelper;
    TextView week_cell_text;


    public WeekListAdapter(@NonNull Context context, Calendar calendar, List<Event> events) {
        super(context, R.layout.weel_cell_layout);
        this.events = events;
        this.calendar = calendar;
        dbOpenHelper = new DBOpenHelper(context);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.weel_cell_layout, null);
        week_cell_text = view.findViewById(R.id.week_cell_text);
        week_cell_text.setText(events.get(position).getTitle()+"");
        int random_color = (int)(Math.random()*5);
        switch (random_color){
            case 0:
                week_cell_text.setBackgroundColor(view.getResources().getColor(R.color.colorRed));
                break;
            case 1:
                week_cell_text.setBackgroundColor(view.getResources().getColor(R.color.colorPink));
                break;
            case 2:
                week_cell_text.setBackgroundColor(view.getResources().getColor(R.color.colorDarkBlue));
                break;
            case 3:
                week_cell_text.setBackgroundColor(view.getResources().getColor(R.color.colorLightBlue));
                break;
            case 4:
                week_cell_text.setBackgroundColor(view.getResources().getColor(R.color.colorBlue));
                break;
        }



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
