package com.example.sketch2;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sketch2.Pojo.Row;
import com.example.sketch2.Pojo.MyPOJO;
import com.example.sketch2.adapter.DangerAdapter;

import java.util.ArrayList;
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
    private DangerAdapter dangerAdapter;

    DataService dataService = new DataService();
    String apikey = "33e3c8bc9f3043c7838c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewExpanded();

        if(searchManager !=null && searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setIconifiedByDefault(false);
            searchView.setOnQueryTextListener(queryTextListener);
        }

        return true;
    }


    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("searchSubmittedQuery ", query);
            dangerAdapter.setFilter(filter(dangerList, query));

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            dangerAdapter.setFilter(filter(dangerList, newText));

            return true;
        }
    };

    private List<Row> filter (List<Row> dangerList, String query){
        query = query.toLowerCase();
        Log.d("lowerCaseQuery", "|"+query+"|");

        List<Row> filteredDangerList = new ArrayList<>();

        if(query!=null && !query.equals("")){
            for(int i=0; i<dangerList.size();i++){
                final String product_name = dangerList.get(i).getPRDTNM();
                final String company_name = dangerList.get(i).getBSSHNM();
                //Log.d("product name", product_name);

                if(product_name.contains(query)) {
                    filteredDangerList.add(dangerList.get(i));
                    //Log.d("filteredDanger","succeed!!");
                } else if(company_name.contains(query)) {
                    filteredDangerList.add(dangerList.get(i));
                } //
            }
        } else filteredDangerList = dangerList;
        return filteredDangerList;
    }

    private void setAdapter(RecyclerView recyclerView){
        dangerAdapter = new DangerAdapter(dangerList);
        dangerAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),OpenDetailActivity.class);
                intent.putExtra("dangerDetail", (Parcelable) dangerList.get(danger.getChildAdapterPosition(view)));

                view.getContext().startActivity(intent);
            }
        });
        recyclerView.setAdapter(dangerAdapter);
    }
}