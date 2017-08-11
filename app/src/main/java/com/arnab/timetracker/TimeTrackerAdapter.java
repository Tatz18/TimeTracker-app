package com.arnab.timetracker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static java.sql.Types.NULL;

/**
 * Created by Arnab on 7/29/2017.
 */

public class TimeTrackerAdapter extends CursorAdapter {

    public TimeTrackerAdapter(Context context,Cursor cursor) {
        super(context,cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.time_list_item,parent,false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.time_view);
        nameTextView.setText(cursor.getString(cursor.getColumnIndex("time")));

        TextView valueTextView = (TextView) view.findViewById(R.id.notes_view);
        valueTextView.setText(cursor.getString(cursor.getColumnIndex("notes")));
    }
}
