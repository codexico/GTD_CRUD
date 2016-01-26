package com.example.fk.gtd_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fk.gtd_crud.R;
import com.example.fk.gtd_crud.model.Stuff;

import java.util.List;

public class StuffAdapter extends BaseAdapter {

    private final Context context;
    private final List<Stuff> stuffList;

    public StuffAdapter(Context context, List<Stuff> stuffList) {
        this.context = context;
        this.stuffList = stuffList;
    }

    @Override
    public int getCount() {
        return stuffList.size();
    }

    @Override
    public Object getItem(int position) {
        return stuffList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stuffList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Stuff stuff = stuffList.get(position);


        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_stuff, parent, false);

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            // trying to do a layout for tablet
//            holder.tvContact = (TextView) convertView.findViewById(R.id.tvContact);
//            holder.tvContext = (TextView) convertView.findViewById(R.id.tvContext);
//            holder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(stuff.getTitle());
        holder.tvDescription.setText(stuff.getDescription());
        // trying to do a layout for tablet
//        holder.tvContact.setText(stuff.contact);
//        holder.tvContext.setText(stuff.context);
//        holder.tvLocation.setText(stuff.location);

        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        // trying to do a layout for tablet
//        TextView tvContact;
//        TextView tvContext;
//        TextView tvLocation;
    }
}
