package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private static final String t = "UserListAdapter";
    private Context mContext;
    private ArrayList<User> users;
    private int mResources;

    public UserAdapter(@NonNull Context context, int resources,@NonNull ArrayList<User> object){
        super(context, resources, object);
        this.mContext = context;
        this.mResources = resources;
        this.users = object;
    }

    @Override
    public int getCount(){
        return users.size();
    }

    @Nullable
    @Override
    public User getItem(int position){
        return users.get(position);
    }

    @Override
    public int getPosition(@Nullable User user){
        // to get the position of our user
        return users.indexOf(user);
    }

    @Override
    public long getItemId(int position) {
        // to get the user by Id
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        // to get the view in a given position
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(mResources,parents,false);
        }
        TextView Id = (TextView) convertView.findViewById(R.id.Id);
        TextView Fname = (TextView) convertView.findViewById(R.id.Fname);
        TextView Lname = (TextView) convertView.findViewById(R.id.Lname);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
        TextView email = (TextView) convertView.findViewById(R.id.email);

        Id.setText(getItem(position).getId_str());
        Fname.setText(getItem(position).getF_name());
        Lname.setText(getItem(position).getL_name());
        Picasso.with(mContext).load(getItem(position).getAvatar().toString()).into(avatar);
        email.setText(getItem(position).getEmail().toString());

        return convertView;
    }

}
