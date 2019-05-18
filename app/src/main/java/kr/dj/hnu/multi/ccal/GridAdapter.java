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

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {


    List<Date> dates;
    List<Event> events;
    LayoutInflater inflater;
    int current_month;
    DBOpenHelper dbOpenHelper;






    public GridAdapter(@NonNull Context context, List<Date> dates, List<Event> events, int current_month) {
        super(context, R.layout.month_cell_layout);
        this.dates = dates;
        this.events = events;
        this.current_month = current_month;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = convertView == null ? inflater.inflate(R.layout.month_cell_layout, null) : convertView;
        View view = inflater.inflate(R.layout.month_cell_layout, null);
        Date date = dates.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        TextView date_view = view.findViewById(R.id.month_cell_date);



        date_view.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        if(calendar.get(Calendar.MONTH) == current_month){
            view.setBackgroundColor(Color.parseColor("#ADD5F7"));
        }else{
            view.setBackgroundColor(Color.parseColor("#4E7AC7"));
            date_view.setTextColor(Color.parseColor("#FFFFFF"));
        }
        // 달력 모양 출력 끝

        //이벤트 개수 출력 시작
        TextView events_view = (TextView)view.findViewById(R.id.month_cell_event);
        dbOpenHelper = new DBOpenHelper(getContext());
        int event_num = dbOpenHelper.getEventNum(calendar);
        if(event_num > 0){
            events_view.setText(event_num+" events");
        }


        return view;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }






}
