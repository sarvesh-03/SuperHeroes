package com.example.superheroes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.JsonNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Details newInstance(String param1, String param2) {
        Details fragment = new Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private superHero superHero;
    private ArrayList<Data> dataArrayList=new ArrayList<>();
    private ListView listView;
    private DataAdapter dataAdapter;
    private List<FavHero> superHeroes;
    private AppDataBase appDataBase;
    private FavDao favDao;
    private ImageView favImage;
    private ImageView share;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details, container, false);
        superHero=(superHero)getArguments().getSerializable("hero");
        appDataBase= Room.databaseBuilder(getContext(),AppDataBase.class,"FavDataBase").allowMainThreadQueries().build();
        favDao=appDataBase.favDao();
        favImage=view.findViewById(R.id.favimage);
        share=view.findViewById(R.id.share);
        image=view.findViewById(R.id.imageViewHero);
        if(superHero!=null){
            superHeroes=favDao.getById(superHero.getId());
        }
        if(superHeroes.size()==0&&superHero!=null){
            favImage.setVisibility(View.VISIBLE);

            share.setVisibility(View.INVISIBLE);
        }
        else{
            share.setVisibility(View.VISIBLE);
            favImage.setVisibility(View.INVISIBLE);


        }
        favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favDao.Insert(new FavHero(superHero.getId(),superHero.getName()));
                favImage.setVisibility(View.INVISIBLE);
                share.setVisibility(View.VISIBLE);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareData();
            }
        });
        if(superHero.getName()!=null)
        dataArrayList.add(new Data("Name",superHero.getName()));
        if(superHero.getId()!=null)
        dataArrayList.add(new Data("Id",superHero.getId()));
        if(superHero.getSlug()!=null)
        dataArrayList.add(new Data("Slug",superHero.getSlug()));
        dataArrayList.add(new Data("",""));
        if(!superHero.getPowerstats().get("intelligence").isJsonNull())
            dataArrayList.add(new Data("Intelligence",superHero.getPowerstats().get("intelligence").getAsString()));
        if(!superHero.getPowerstats().get("strength").isJsonNull())
            dataArrayList.add(new Data("Strength",superHero.getPowerstats().get("strength").getAsString()));
        if(!superHero.getPowerstats().get("speed").isJsonNull())
        dataArrayList.add(new Data("Speed",superHero.getPowerstats().get("speed").getAsString()));
        if(!superHero.getPowerstats().get("durability").isJsonNull())
        dataArrayList.add(new Data("Durability",superHero.getPowerstats().get("durability").getAsString()));
        if(!superHero.getPowerstats().get("power").isJsonNull())
        dataArrayList.add(new Data("Power",superHero.getPowerstats().get("power").getAsString()));
        if(!superHero.getPowerstats().get("combat").isJsonNull())
        dataArrayList.add(new Data("Combat",superHero.getPowerstats().get("combat").getAsString()));
        dataArrayList.add(new Data("",""));
        if(!superHero.getAppearance().get("gender").isJsonNull())
        dataArrayList.add(new Data("Gender",superHero.getAppearance().get("gender").getAsString()));
        if(!superHero.getAppearance().get("race").isJsonNull())
        dataArrayList.add(new Data("Race",superHero.getAppearance().get("race").getAsString()));
        if(!superHero.getAppearance().get("height").isJsonNull())
        dataArrayList.add(new Data("Height",superHero.getAppearance().get("height").getAsJsonArray().toString()));
        if(!superHero.getAppearance().get("weight").isJsonNull())
            dataArrayList.add(new Data("Weight",superHero.getAppearance().get("weight").getAsJsonArray().toString()));
        if(!superHero.getAppearance().get("eyeColor").isJsonNull())
        dataArrayList.add(new Data("Eye Color",superHero.getAppearance().get("eyeColor").getAsString()));
        if(!superHero.getAppearance().get("hairColor").isJsonNull())
        dataArrayList.add(new Data("HairColor",superHero.getAppearance().get("hairColor").getAsString()));
        dataArrayList.add(new Data("",""));
        if(!superHero.getBiography().get("fullName").isJsonNull())
        dataArrayList.add(new Data("Full Name",superHero.getBiography().get("fullName").getAsString()));
        if(!superHero.getBiography().get("alterEgos").isJsonNull())
        dataArrayList.add(new Data("Alter Egos",superHero.getBiography().get("alterEgos").getAsString()));
        if(!superHero.getBiography().get("aliases").isJsonNull())
            dataArrayList.add(new Data("Aliases",superHero.getBiography().get("aliases").getAsJsonArray().toString()));
        if(!superHero.getBiography().get("placeOfBirth").isJsonNull())
        dataArrayList.add(new Data("Place Of Birth",superHero.getBiography().get("placeOfBirth").getAsString()));
        if(!superHero.getBiography().get("firstAppearance").isJsonNull())
        dataArrayList.add(new Data("First Appearance",superHero.getBiography().get("firstAppearance").getAsString()));
        if(!superHero.getBiography().get("publisher").isJsonNull())
        dataArrayList.add(new Data("Publisher",superHero.getBiography().get("publisher").getAsString()));
        if(!superHero.getBiography().get("alignment").isJsonNull())
        dataArrayList.add(new Data("Alignment",superHero.getBiography().get("alignment").getAsString()));
        dataArrayList.add(new Data("",""));
        if(!superHero.getWork().get("occupation").isJsonNull())
        dataArrayList.add(new Data("Occupation",superHero.getWork().get("occupation").getAsString()));
        if(!superHero.getWork().get("base").isJsonNull())
        dataArrayList.add(new Data("Base",superHero.getWork().get("base").getAsString()));
        dataArrayList.add(new Data("",""));
        if(!superHero.getConnections().get("groupAffiliation").isJsonNull())
        dataArrayList.add(new Data("Group Affiliation",superHero.getConnections().get("groupAffiliation").getAsString()));
        if(!superHero.getConnections().get("relatives").isJsonNull())
        dataArrayList.add(new Data("Relatives",superHero.getConnections().get("relatives").getAsString()));
        Picasso.get().load(superHero.getImages().get("md").getAsString()).resize(250,250).centerCrop().into((ImageView)view.findViewById(R.id.imageViewHero));
        listView=view.findViewById(R.id.listview);
        dataAdapter=new DataAdapter(getContext(),dataArrayList);
        listView.setAdapter(dataAdapter);
        return view;
    }

    public void ShareData() {
        if (image.getDrawable() != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            String bitmapPath = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "Dog", null);
            Intent intent = new Intent(Intent.ACTION_SEND);
            Uri bitmapUri = Uri.parse(bitmapPath);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
            intent.putExtra(Intent.EXTRA_TEXT, "NAME" + ":" + superHero.getName());
            startActivity(Intent.createChooser(intent, "Share Image"));
        }
    }
}