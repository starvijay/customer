package com.example.satmunia.myapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by SatMunia on 19-04-2018.
 */

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<SearchCustomer> customerlist = null;
    private ArrayList<SearchCustomer> arraylist;

    public ListViewAdapter(Context context, List<SearchCustomer> customerlist) {
        mContext = context;
        this.customerlist = customerlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SearchCustomer>();
        this.arraylist.addAll(customerlist);
    }
    public void setAll( List<SearchCustomer> customerlist) {
        arraylist.clear();
        arraylist.addAll(customerlist);
    }

    public class ViewHolder {
        TextView name;
        TextView email;
        TextView mobile;
        TextView location;
    }

    @Override
    public int getCount() {
        return customerlist.size();
    }

    @Override
    public SearchCustomer getItem(int position) {
        return customerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.email = (TextView) view.findViewById(R.id.email);
            holder.mobile = (TextView) view.findViewById(R.id.mobile);
            holder.location = (TextView) view.findViewById(R.id.location);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(customerlist.get(position).getName());
        holder.email.setText(customerlist.get(position).getEmail());
        holder.mobile.setText(customerlist.get(position).getMobile());
        holder.location.setText(customerlist.get(position).getLocation());

                // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mContext, IntermediateView.class);
                intent.putExtra("Name",(customerlist.get(position).getName()));
                intent.putExtra("Email",(customerlist.get(position).getEmail()));
                intent.putExtra("Mobile",(customerlist.get(position).getMobile()));
                intent.putExtra("Location",(customerlist.get(position).getLocation()));
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        customerlist.clear();
        if (charText.length() == 0) {
            customerlist.addAll(arraylist);
        }
        else
        {
            for (SearchCustomer wp : arraylist)
            {
                Log.e("name",wp.getName());
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    customerlist.add(wp);
                }
                else if (wp.getEmail().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    customerlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}