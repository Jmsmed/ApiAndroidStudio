package com.example.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ListView listView; // our list view
    ArrayList<User> arrayList; // Creating an arraylist of history
    UserAdapter arrayAdapter; // creating our arrayAdapter
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: Started");
        listView = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<User>();
        arrayAdapter = new UserAdapter(this, R.layout.row,arrayList); //As you can see we're affecting this adapter to another layout
        listView.setAdapter(arrayAdapter);
    }

    public void fetchUser(View view){
        TextView FNameView = (TextView) findViewById(R.id.Fname);
        EditText userID = (EditText) findViewById(R.id.nbUser);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi apiHolder = retrofit.create(JsonPlaceHolderApi.class);
        Call<JsonData> call = apiHolder.getUsers( Integer.parseInt(userID.getText().toString()));
        call.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {
                // In case of success
                arrayList.clear();
                User tmpuser =new User(response.body().getId(),response.body().getEmail(),response.body().getFname(),response.body().getLname(),response.body().getAvatar());
                arrayList.add(tmpuser); //adding our History object to the array list
                arrayAdapter.notifyDataSetChanged(); // Update the listView
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {

            }
        });
    }

    public void fetchAll(View view){
        // Connexion with API
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi apiHolder = retrofit.create(JsonPlaceHolderApi.class);
        Call<JsonDataAll> call = apiHolder.getAll();
        call.enqueue(new Callback<JsonDataAll>() {
            @Override
            public void onResponse(Call<JsonDataAll> call, Response<JsonDataAll> response) {
                // In case of success
                arrayList.clear(); //to empty the result fields
                users = (ArrayList<User>) response.body().getAll(); //Get all users from the request
                arrayList.addAll(users); // Add all the users to our ListView
                arrayAdapter.notifyDataSetChanged(); //Notify Changes
            }

            @Override
            public void onFailure(Call<JsonDataAll> call, Throwable t) {
                System.out.println(t.toString()); //case of error
            }
        });
    }
}