package com.example.sketch2;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sketch2.Pojo.Row;
import com.example.sketch2.Pojo.MyPOJO;
import com.example.sketch2.adapter.DangerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private RecyclerView danger;
    private Context context;
    private List<Row> dangerList;
    private TextView test;
    private MyPOJO myPOJO;

    DataService dataService = new DataService();
    String apikey = "33e3c8bc9f3043c7838c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        danger = (RecyclerView)findViewById(R.id.danger);

        danger.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        danger.addItemDecoration(new DividerItemDecoration(this));

        dataService.select.selectAll(apikey).enqueue(new Callback<MyPOJO>() {
            @Override
            public void onResponse(Call<MyPOJO> call, Response<MyPOJO> response) {
                dangerList = response.body().getI0490().getRowList();
                setAdapter(danger);
            }

            @Override
            public void onFailure(Call<MyPOJO> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void setAdapter(RecyclerView recyclerView){
        final DangerAdapter dangerAdapter = new DangerAdapter(dangerList);
        dangerAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),OpenDetailActivity.class);
                //intent.putExtra("id", danger.getChildAdapterPosition(view));
                intent.putExtra("dangerDetail", (Parcelable) dangerList.get(danger.getChildAdapterPosition(view)));

                view.getContext().startActivity(intent);
            }
        });
        recyclerView.setAdapter(dangerAdapter);
    }
}