package com.example.shortstoryEnApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shortstoryEnApp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView home,faborites,setting;
  private RecyclerView rv;

    private DB_Methods DB;
    private RV_Adapter adapter;
    ArrayList<Item_Parameters> arrayData;
private  InterstitialAd mInterstitialAd;
    private int  story_type =0;

    private boolean isApprunning=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
getSupportActionBar().hide();
        //home=findViewById(R.id.home_ic_id);
        home=findViewById(R.id.home_ic_id);
        faborites=findViewById(R.id.faborite_ic_id);
        setting=findViewById(R.id.setting_ic_id);
        rv=findViewById(R.id.rv_id);
        // to get data and add it in recycler view :


        DB=DB_Methods.getInstance(this);
        getDATA(0);
        adapter=new RV_Adapter(arrayData, new interfaceItem() {
            @Override
            public void OnclickItemRV(int story_id) {
                Intent TostoryText=new Intent(MainActivity.this, StoryText_layout.class);
                TostoryText.putExtra("id",story_id);
                TostoryText.putExtra("story_type",story_type);
                startActivity(TostoryText);
            }
        });
        RecyclerView.LayoutManager manager=new GridLayoutManager(this,1);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

        home.setOnClickListener(this);
       faborites.setOnClickListener(this);
       setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_ic_id:
                story_type=0;
                getDATA(story_type);
                adapter.setArrdata(arrayData);
                adapter.notifyDataSetChanged();
                break;
            case R.id.faborite_ic_id:
                story_type=1;
              getDATA(story_type);
              adapter.setArrdata(arrayData);
                adapter.notifyDataSetChanged();

                break;
            case R.id.setting_ic_id:
                Intent settingintent=new Intent(MainActivity.this, SettingLayout.class);
                startActivity(settingintent);
                break;
        }
    }

    private void getDATA(int storytype){
        DB.open();
        arrayData=DB.getStories(storytype);
        DB.close();
    }
    private void onLoadAd(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-5174444665077361/7040946991", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        if(mInterstitialAd!=null)
                            mInterstitialAd.show(MainActivity.this);
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isApprunning==true) {onLoadAd();}
            }
        },10000);
        isApprunning =true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isApprunning=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isApprunning=false;
    }
}