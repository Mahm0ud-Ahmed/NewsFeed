package com.example.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ListAdapter extends ArrayAdapter<Fields> {
    private List<Fields> list;
    private int mActivityColor;
    FragmentAdapter adapter;

    public ListAdapter(@NonNull Context context, @NonNull List<Fields> list, int mActivityColor) {
        super(context, 0, list);

        this.mActivityColor = mActivityColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Fields field = getItem(position);
        ViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_templet, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(field.getTitle());
        holder.tv_date.setText(splitDate(field.getDate(), 1));
        holder.tv_time.setText(splitDate(field.getDate(), 2));
        String section = field.getSection();
        if (!section.equals("")) {
            holder.tv_section.setVisibility(View.VISIBLE);
            holder.tv_section.setText(section);
        }else {
            holder.tv_section.setVisibility(View.GONE);
        }

        return view;
    }

    private String splitDate(String date, int index){
        String dateTime = formatDate(date);
        String temp;
        if(!dateTime.isEmpty()) {
            String[] subArray = dateTime.split("T", 2);
            switch (index){
                case 1:
                case 2:
                    temp = subArray[index-1];
                    return temp;
                default:
                    return null;
            }
        }
        return null;
    }

    private String formatDate(String date){
        String parseDate = null;
        if (!date.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date dateParse = dateFormat.parse(date);
                dateFormat.setTimeZone(TimeZone.getDefault());
                parseDate = dateFormat.format(dateParse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return parseDate;
    }

    private class ViewHolder{
        private TextView tv_title, tv_date, tv_time, tv_section;
        private View view;

        public ViewHolder(View view) {
            tv_title = view.findViewById(R.id.title);
            tv_date = view.findViewById(R.id.date);
            tv_time = view.findViewById(R.id.time);
            tv_section = view.findViewById(R.id.section);
            this.view = view.findViewById(R.id.layout_parent);
            this.view.setBackgroundColor(ContextCompat.getColor(getContext(),mActivityColor));
        }

    }
}
