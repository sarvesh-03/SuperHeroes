package com.example.superheroes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link male#newInstance} factory method to
 * create an instance of this fragment.
 */
public class male extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public male() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment male.
     */
    // TODO: Rename and change types and number of parameters
    public static male newInstance(String param1, String param2) {
        male fragment = new male();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private  final Retrofit retrofit=new Retrofit.Builder().baseUrl("https://akabab.github.io/superhero-api/api/").
            addConverterFactory(GsonConverterFactory.create())
            .build();
    private final MyApi myApi=retrofit.create(MyApi.class);
    private CardAdapter cardAdapter=new CardAdapter();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private BlankFragment.Listener listener;
    private SearchView searchView;
    private CardAdapter searchAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener=(BlankFragment.Listener)context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        View view=inflater.inflate(R.layout.fragment_male, container, false);
        recyclerView=view.findViewById(R.id.recyclerview2);
        progressBar=view.findViewById(R.id.progressBar2);
        if(recyclerView.getLayoutManager()==null)
            recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);
        searchView=view.findViewById(R.id.searchView2);
        searchAdapter=new CardAdapter();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchCard(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setAdapter(cardAdapter);
                return false;
            }
        });
        recyclerView.addOnItemTouchListener(new RecycleViewListener(getContext(), recyclerView, new RecycleViewListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                listener.inputSent(((CardAdapter)recyclerView.getAdapter()).getItem(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        if(cardAdapter.getItemCount()==0) {
            Call<List<superHero>> call = myApi.getAll();
            call.enqueue(new Callback<List<superHero>>() {
                @Override
                public void onResponse(Call<List<superHero>> call, Response<List<superHero>> response) {
                    if (response.code() == 200) {
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < response.body().size(); i++) {
                            if (response.body().get(i).getAppearance().get("gender").getAsString().equals("Male"))
                                cardAdapter.add(response.body().get(i));
                        }
                        Log.v("dataFetched", "" + response.body().get(0).getName() + " " + cardAdapter.getItemCount());

                    }
                    Log.v("dataFetched", "" + response.code());
                }

                @Override
                public void onFailure(Call<List<superHero>> call, Throwable t) {
                    Log.v("fail", "failed");

                }
            });
        }
        else progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    public void SearchCard(String query) {
        Log.v("sea",""+searchAdapter.getItemCount());
        if(searchAdapter.getItemCount()!=0)
            searchAdapter.clear();
        Log.v("sea1",""+searchAdapter.getItemCount());
        if(cardAdapter.getItemCount()!=0){
            for(int i=0;i<cardAdapter.getItemCount();i++){
                if(cardAdapter.getItem(i).getName().contains(query)||cardAdapter.getItem(i).getId().equals(query)){
                    searchAdapter.add(cardAdapter.getItem(i));
                }
            }
            recyclerView.setAdapter(searchAdapter);
        }

    }
}