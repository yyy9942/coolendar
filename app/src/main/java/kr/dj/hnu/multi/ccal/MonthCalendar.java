package kr.dj.hnu.multi.ccal;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonthCalendar extends Fragment {
    final static int MAX_DAY_OF_CALENDAR = 42;
    Calendar calendar;
    TextView month_cell_month;
    GridView gridView;
    ImageButton btn_privious;
    ImageButton btn_next;
    List<Date> dates;
    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_calendar_layout, container, false);
        month_cell_month = view.findViewById(R.id.daily_textview);
        gridView = view.findViewById(R.id.gridView);
        calendar = Calendar.getInstance();
        btn_next = view.findViewById(R.id.btn_daily_next);
        btn_privious = view.findViewById(R.id.btn_daily_previous);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setCalendar();
            }
        });

        btn_privious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view =LayoutInflater.from(parent.getContext()).inflate(R.layout.new_event_layout, null);
                final EditText event_title = event_view.findViewById(R.id.event_title);
                final EditText event_content = event_view.findViewById(R.id.event_content);
                final TextView event_time = event_view.findViewById(R.id.event_time);
                final Button btn_add_event = event_view.findViewById(R.id.btn_add_event);
                Date event_date = dates.get(position);
                final Calendar event_calendar = Calendar.getInstance();
                event_calendar.setTime(event_date);
                event_time.setText((event_calendar.get(Calendar.MONTH)+1) + "월 " + event_calendar.get(Calendar.DAY_OF_MONTH) + "일");
                btn_add_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBOpenHelper dbOpenHelper = new DBOpenHelper(parent.getContext());
                        Event event = new Event(event_title.getText().toString(),
                                event_content.getText().toString(),
                                event_calendar.get(Calendar.DAY_OF_MONTH)+"",
                                (event_calendar.get(Calendar.MONTH)+1)+"",
                                event_calendar.get(Calendar.YEAR)+"");
                        dbOpenHelper.saveEvent(event);
                        setCalendar();
                        alertDialog.dismiss();
                    }
                });
                builder.setView(event_view);
                alertDialog =builder.create();
                alertDialog.show();





            }
        });





       setCalendar();



        return view;
    }

    public void setCalendar(){
        dates = new ArrayList<>();
        month_cell_month.setText((calendar.get(Calendar.MONTH) + 1) + "월");

        Calendar monthCalendar = (Calendar)calendar.clone();

        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int first_day = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -first_day);

        while(dates.size() < MAX_DAY_OF_CALENDAR) {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        GridAdapter gridAdapter = new GridAdapter(getContext(), dates, new ArrayList<Event>(), calendar.get(Calendar.MONTH));
        gridView.setAdapter(gridAdapter);

    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
}
