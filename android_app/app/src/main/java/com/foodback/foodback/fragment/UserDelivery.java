package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.config.MealEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Meal;
import com.foodback.foodback.utils.CategoryUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDelivery extends Fragment {

    List<Establishment> estabs1, estabs2, estabs3, estabs4;
    Spinner category_choice1, category_choice2, category_choice3, category_choice4;
    Spinner estab_choice1, estab_choice2, estab_choice3, estab_choice4;
    Spinner menu_choice1, menu_choice2, menu_choice3, menu_choice4;
    Spinner quantity_choice1, quantity_choice2, quantity_choice3, quantity_choice4;

    CategoryUtils catUtils;

    EstablishmentEndpoints establishmentServices;
    MealEndpoints mealServices;

    Spinner estab_choice;
    Spinner menu_choice;

    public UserDelivery() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_user_delivery, container, false);

        category_choice1 = view.findViewById(R.id.category_choice1);
        category_choice2 = view.findViewById(R.id.category_choice2);
        category_choice3 = view.findViewById(R.id.category_choice3);
        category_choice4 = view.findViewById(R.id.category_choice4);

        estab_choice1 = view.findViewById(R.id.estab_choice1);
        estab_choice2 = view.findViewById(R.id.estab_choice2);
        estab_choice3 = view.findViewById(R.id.estab_choice3);
        estab_choice4 = view.findViewById(R.id.estab_choice4);

        menu_choice1 = view.findViewById(R.id.menu_choice1);
        menu_choice2 = view.findViewById(R.id.menu_choice2);
        menu_choice3 = view.findViewById(R.id.menu_choice3);
        menu_choice4 = view.findViewById(R.id.menu_choice4);

        quantity_choice1 = view.findViewById(R.id.quantity_choice1);
        quantity_choice2 = view.findViewById(R.id.quantity_choice2);
        quantity_choice3 = view.findViewById(R.id.quantity_choice3);
        quantity_choice4 = view.findViewById(R.id.quantity_choice4);

        catUtils = new CategoryUtils();

        catUtils.populateSpinner(getActivity(), category_choice1, null);
        catUtils.populateSpinner(getActivity(), category_choice2, null);
        catUtils.populateSpinner(getActivity(), category_choice3, null);
        catUtils.populateSpinner(getActivity(), category_choice4, null);

        establishmentServices = retrofit.create(EstablishmentEndpoints.class);
        mealServices = retrofit.create(MealEndpoints.class);

        category_choice1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateEstablishmentSpinner(view, 1, position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        category_choice2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateEstablishmentSpinner(view, 2, position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        category_choice3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateEstablishmentSpinner(view, 3, position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        category_choice4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateEstablishmentSpinner(view, 4, position+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        estab_choice1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateMenuSpinner(view, 1, position-1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        estab_choice2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateMenuSpinner(view, 2, position-1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        estab_choice3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateMenuSpinner(view, 3, position-1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
        estab_choice4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                populateMenuSpinner(view, 4, position-1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });


        return view;
    }

    private void populateMenuSpinner(View view, int k, int i) {
        List<Establishment> estabs = null;
        switch(k) {
            case 1:
                estabs = estabs1;
                menu_choice = menu_choice1;
                break;
            case 2:
                estabs = estabs2;
                menu_choice = menu_choice2;
                break;
            case 3:
                estabs = estabs3;
                menu_choice = menu_choice3;
                break;
            case 4:
                estabs = estabs4;
                menu_choice = menu_choice4;
                break;
            default:
                break;
        }
        try {
            if(estabs != null) {
                Call<List<Meal>> call = mealServices.getAllMeals(estabs.get(i).getId());

                call.enqueue(new Callback<List<Meal>>() {
                    @Override
                    public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                        if(response.isSuccessful()) {
                            List<Meal> meals = response.body();
                            if(meals.size() != 0) {
                                List<String> spinnerArray = new ArrayList<>();

                                spinnerArray.add("");
                                for(Meal x: meals) {
                                    spinnerArray.add(x.getMeal());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                        getActivity(),
                                        android.R.layout.simple_spinner_item,
                                        spinnerArray);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                menu_choice.setAdapter(adapter);
                            }
                        } else {
                            isBad(getActivity(), response);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Meal>> call, Throwable t) {
                        isFailure(getActivity(), t);
                    }
                });
            }
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void populateEstablishmentSpinner(View view, final int k, long id) {
        switch(k) {
            case 1:
                estab_choice = estab_choice1;
                break;
            case 2:
                estab_choice = estab_choice2;
                break;
            case 3:
                estab_choice = estab_choice3;
                break;
            case 4:
                estab_choice = estab_choice4;
                break;
            default:
                break;
        }
        try {
            Call<List<Establishment>> call = establishmentServices.getEstablishmentsFiltered(id);

            call.enqueue(new Callback<List<Establishment>>() {
                @Override
                public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                    if(response.isSuccessful()) {
                        List<Establishment> estabs = response.body();
                        switch(k) {
                            case 1:
                                estabs1 = estabs;
                                break;
                            case 2:
                                estabs2 = estabs;
                                break;
                            case 3:
                                estabs3 = estabs;
                                break;
                            case 4:
                                estabs4 = estabs;
                                break;
                            default:
                                break;
                        }
                        if(estabs.size() != 0) {
                            List<String> spinnerArray = new ArrayList<>();

                            spinnerArray.add("");
                            for(Establishment x: estabs) {
                                spinnerArray.add(x.getName());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerArray);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            estab_choice.setAdapter(adapter);
                        }
                    } else {
                        isBad(getActivity(), response);
                    }
                }
                @Override
                public void onFailure(Call<List<Establishment>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
