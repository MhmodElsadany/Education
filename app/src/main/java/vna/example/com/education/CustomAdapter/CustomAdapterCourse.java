package vna.example.com.education.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vna.example.com.education.Models.ModelCourse;
import vna.example.com.education.R;

/**
 * Created by Google       Company on 11/05/2017.
 */

public class CustomAdapterCourse extends BaseAdapter {
    ArrayList<ModelCourse> itemsCourse;
    Context mContext;

    public CustomAdapterCourse(ArrayList<ModelCourse> itemsCourse,Context mContext) {
        this.itemsCourse = itemsCourse;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        Log.i("sizzzzzzzzzze",itemsCourse.size()+"");
        return itemsCourse.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsCourse.get(i).getName_course();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.row_info_course,viewGroup,false);

        TextView name_course = (TextView) view.findViewById(R.id.name_course);
        name_course.setText(itemsCourse.get(i).getName_course());
        Log.i("sizzzzzzzzzze",itemsCourse.get(i).getName_course());


        return view;
    }
}