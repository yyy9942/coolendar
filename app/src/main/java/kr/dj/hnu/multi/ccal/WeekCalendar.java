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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class WeekCalendar extends Fragment {
    Calendar calendar;
    TextView week_textview;
    ImageButton btn_week_previous;
    ImageButton btn_week_next;
    TextView week_0;
    TextView week_1;
    TextView week_2;
    TextView week_3;
    TextView week_4;
    TextView week_5;
    TextView week_6;
    ListView week_listView_0;
    ListView week_listView_1;
    ListView week_listView_2;
    ListView week_listView_3;
    ListView week_listView_4;
    ListView week_listView_5;
    ListView week_listView_6;
    WeekListAdapter weekListAdapter0;
    WeekListAdapter weekListAdapter1;
    WeekListAdapter weekListAdapter2;
    WeekListAdapter weekListAdapter3;
    WeekListAdapter weekListAdapter4;
    WeekListAdapter weekListAdapter5;
    WeekListAdapter weekListAdapter6;
    DBOpenHelper dbOpenHelper;
    Calendar week_calendar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_calendar_layout, container, false);
        dbOpenHelper = new DBOpenHelper(getContext());

        calendar = Calendar.getInstance();
        week_textview = view.findViewById(R.id.daily_textview);
        btn_week_previous = view.findViewById(R.id.btn_daily_previous);
        btn_week_next = view.findViewById(R.id.btn_daily_next);
        week_0 = view.findViewById(R.id.week_0);
        week_1 = view.findViewById(R.id.week_1);
        week_2 = view.findViewById(R.id.week_2);
        week_3 = view.findViewById(R.id.week_3);
        week_4 = view.findViewById(R.id.week_4);
        week_5 = view.findViewById(R.id.week_5);
        week_6 = view.findViewById(R.id.week_6);
        week_listView_0 = view.findViewById(R.id.week_listview0);
        week_listView_1 = view.findViewById(R.id.week_listview1);
        week_listView_2 = view.findViewById(R.id.week_listview2);
        week_listView_3 = view.findViewById(R.id.week_listview3);
        week_listView_4 = view.findViewById(R.id.week_listview4);
        week_listView_5 = view.findViewById(R.id.week_listview5);
        week_listView_6 = view.findViewById(R.id.week_listview6);



        btn_week_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.WEEK_OF_MONTH, -1);
                setCalendar();
            }
        });

        btn_week_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                setCalendar();
            }
        });
        setCalendar();

        return view;
    }

    public void setCalendar(){
        week_textview.setText((calendar.get(Calendar.MONTH)+1) + "월");
        week_calendar = (Calendar)calendar.clone();
        week_calendar.set(Calendar.DAY_OF_WEEK, 1);

        week_0.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter0 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_0.setAdapter(weekListAdapter0);
        week_listView_0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 1);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_1.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter1 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_1.setAdapter(weekListAdapter1);
        week_listView_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 2);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_2.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter2 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_2.setAdapter(weekListAdapter2);
        week_listView_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 3);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_3.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter3 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_3.setAdapter(weekListAdapter3);
        week_listView_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 4);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_4.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter4 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_4.setAdapter(weekListAdapter4);
        week_listView_4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 5);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_5.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter5 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_5.setAdapter(weekListAdapter5);
        week_listView_5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 6);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        week_calendar.add(Calendar.DAY_OF_MONTH, 1);
        week_6.setText(week_calendar.get(Calendar.DAY_OF_MONTH)+"");
        weekListAdapter6 = new WeekListAdapter(getContext(), calendar, dbOpenHelper.getEvents(week_calendar));
        week_listView_6.setAdapter(weekListAdapter6);
        week_listView_6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar event_calendar = (Calendar)week_calendar.clone();
                event_calendar.set(Calendar.DAY_OF_WEEK, 7);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View event_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_cell_layout, null);
                TextView event_title = event_view.findViewById(R.id.daily_event_title);
                TextView event_content = event_view.findViewById(R.id.daily_event_content);
                List<Event> events = dbOpenHelper.getEvents(event_calendar);
                Event event = events.get(position);
                event_title.setText(event.getTitle().toString());
                event_content.setText(event.getContent().toString());
                builder.setView(event_view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        // 주별 달력 출력 끝

    }
}
