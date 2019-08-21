package com.developer.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.developer.myapplication.retrofit.APIClient;
import com.developer.myapplication.retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list;
    private HinhNenAdapter hinhNenAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataClient dataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rc);
        swipeRefreshLayout = findViewById(R.id.f5);
        dataClient = APIClient.getData();
        list = new ArrayList<>();
        hinhNenAdapter = new HinhNenAdapter(this, list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hinhNenAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });


    }


    private void getData() {
        list.clear();
        dataClient.getDataALL().enqueue(new Callback<HinhNen>() {
            @Override
            public void onResponse(Call<HinhNen> call, Response<HinhNen> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                Log.e("ABC", response.body().getStat() + "");

                for (Photo photo : response.body().getPhotos().getPhoto()) {

                    list.add(photo.getUrlC());
                    list.add(photo.getUrlL());
                    list.add(photo.getUrlM());
                    list.add(photo.getUrlN());
                    list.add(photo.getUrlQ());
                    list.add(photo.getUrlC());
                    list.add(photo.getUrlS());
                    list.add(photo.getUrlSq());

                }


                Log.e("LIST", list.size() + "");

                swipeRefreshLayout.setRefreshing(false);

                hinhNenAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<HinhNen> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                getData();
            }
        });


    }
}
