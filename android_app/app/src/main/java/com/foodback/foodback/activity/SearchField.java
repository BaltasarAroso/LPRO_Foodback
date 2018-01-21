package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class SearchField extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    List<Establishment> allEstablishments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_field);

        ListView lv  = findViewById(R.id.search_list);
        ArrayList<String> arraySearch = new ArrayList<>();
        Toast.makeText(this, "A preencher lista de estabelecimentos...", Toast.LENGTH_SHORT).show();
        fillArray(arraySearch);

        adapter = new ArrayAdapter<String>(
                SearchField.this,
                android.R.layout.simple_list_item_1,
                arraySearch
        );
        lv.setAdapter(adapter);
    }

    private void fillArray(final ArrayList<String> possibilities) {
        try{
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Establishment>> call = services.getAllEstablishments();

            call.enqueue(new Callback<List<Establishment>>() {
                @Override
                public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                    if(response.isSuccessful()) {
                        allEstablishments = response.body();
                        for(Establishment x: allEstablishments) {
                            possibilities.add(x.getName());
                        }
                        Collections.sort(possibilities);
                    } else {
//                        APIError apiError = ErrorUtils.parseError(response);
//                        Toast.makeText(getApplicationContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        isBad(getApplicationContext(), response);
                    }
                }

                @Override
                public void onFailure(Call<List<Establishment>> call, Throwable t) {
//                    Log.e("DEBUG",Log.getStackTraceString(t));
//                    Toast.makeText(getApplicationContext(), "Error getting server response.", Toast.LENGTH_SHORT).show();
                    isFailure(getApplicationContext(), t);
                }
            });

        } catch(Exception e) {
//            Log.e("DEBUG",Log.getStackTraceString(e));
//            Toast.makeText(getApplicationContext(), "Unexpected error.", Toast.LENGTH_SHORT).show();
            isException(getApplicationContext(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
