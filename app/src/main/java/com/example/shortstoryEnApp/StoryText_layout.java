package com.example.shortstoryEnApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class StoryText_layout extends AppCompatActivity {
 private TextView text_Story,TitelStory;
  private DB_Methods DB;
    int storyID;
    int sotryType;
   private boolean checkAppwork=false;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_text_layout);
        text_Story=findViewById(R.id.textstory_id);
        TitelStory=findViewById(R.id.texttogetTitel);

    Intent intent=getIntent();
        storyID =intent.getIntExtra("id",0);
        sotryType =intent.getIntExtra("story_type",0);
        DB=DB_Methods.getInstance(this);
        if(storyID!=0){
            if(sotryType==0){
               getStoryText();
            }
            getStoryText();
        }

// Ads :
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-5174444665077361/7248243822");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

// menu :
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MenuIDfaborit:
                DB.open();
                Item_Parameters storyitem=DB.getOneStory(storyID,sotryType);
                boolean res=DB.InsertIntoFaborite(storyitem);
                DB.close();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void  getStoryText(){
      DB.open();
      Item_Parameters storyitem=DB.getOneStory(storyID,sotryType);
      DB.close();
      TitelStory.setText(storyitem.getTitel());
        text_Story.setText(storyitem.getText_story());
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkAppwork=false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkAppwork=true;
    }
}