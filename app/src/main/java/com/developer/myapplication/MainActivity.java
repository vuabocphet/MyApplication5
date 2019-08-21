package com.developer.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
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
        recyclerView.setAdapter(hinhNenAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });


    }


    private void getData() {

        dataClient.getDataALL("flickr.favorites.getList", "24bf810575bc5bfbe2aef1ed6cd4517b", "63356846%40N04", "json", "1", "views,%20media,%20path_alias,%20url_sq,%20url_t,%20url_s,%20url_q,%20url_m,%20url_n,%20url_z,%20url_c,%20url_l,%20url_o", "10", "2").enqueue(new Callback<List<HinhNen>>() {
            @Override
            public void onResponse(Call<List<HinhNen>> call, Response<List<HinhNen>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                list.clear();

                for (HinhNen hinhNen : response.body()) {

                    for (Photo photo : hinhNen.getPhotos().getPhoto()) {

                        list.add(photo.getUrlC());
                        list.add(photo.getUrlL());
                        list.add(photo.getUrlM());
                        list.add(photo.getUrlN());
                        list.add(photo.getUrlQ());
                        list.add(photo.getUrlC());
                        list.add(photo.getUrlS());
                        list.add(photo.getUrlSq());

                    }

                }

                swipeRefreshLayout.setRefreshing(false);

                hinhNenAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<HinhNen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                getData();
            }
        });


    }
}
