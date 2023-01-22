package com.example.shortstoryEnApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_Methods {
    DB_class DB;
    SQLiteDatabase database;
    SQLiteOpenHelper helper;
    static DB_Methods instance;

    private DB_Methods(Context context) {
        this.helper =new DB_class(context);

    }
    public static DB_Methods getInstance(Context context){
        if(instance==null){
            instance=new DB_Methods(context);
        }
        return instance;
    }
    // open and close methods :
 public void open(){
        this.database=this.helper.getWritableDatabase();
 }
  public void close(){
      if(this.database!=null){
          this.database.close();
      }
    }

    // methods to manage data base :
     // get All stories
    public ArrayList<Item_Parameters> getStories(int storytype){
        Cursor cur = null;
        ArrayList<Item_Parameters> arrData=new ArrayList<Item_Parameters>();
        if(storytype==0)
        cur=database.rawQuery(" SELECT* FROM "+DB_class.TABLE_ALL_NAME,null);
        else {
            cur=database.rawQuery(" SELECT* FROM "+DB_class.TABLE_FABORITE,null);
        }
        cur.moveToFirst();
         if(cur!=null&&cur.moveToFirst()){
             do{
                 int id= cur.getInt(cur.getColumnIndexOrThrow(DB_class.ID));
                 String titelstory=cur.getString(cur.getColumnIndexOrThrow(DB_class.TITLE));
                 String text_story=cur.getString(cur.getColumnIndexOrThrow(DB_class.STORY_TEXT));
                 arrData.add(new Item_Parameters(id,titelstory,text_story));
             }while (cur.moveToNext());

             cur.close();}
    return arrData; }

   // get One stories :
    public Item_Parameters getOneStory(int id,int storyType){
        Cursor cur;
        ArrayList<Item_Parameters> arrData=new ArrayList<Item_Parameters>();
        Item_Parameters story = null;
        if(storyType==0)
        cur=database.rawQuery("SELECT* FROM "+DB_class.TABLE_ALL_NAME+" where "+DB_class.ID+" LIKE ?" ,new String[]{String.valueOf(id)});
        else{
            cur=database.rawQuery("SELECT* FROM "+DB_class.TABLE_FABORITE+" where "+DB_class.ID+" LIKE ?" ,new String[]{String.valueOf(id)});
        }
        cur.moveToFirst();
        if(cur!=null&&cur.moveToFirst()){
            //  named ids because id alredy exixste
            int ids= cur.getInt(cur.getColumnIndexOrThrow(DB_class.ID));
            String titelstory=cur.getString(cur.getColumnIndexOrThrow(DB_class.TITLE));
            String text_story=cur.getString(cur.getColumnIndexOrThrow(DB_class.STORY_TEXT));

            story=new Item_Parameters(ids,titelstory,text_story);
        }
   return story; }


    // isert stories int faborite
    public boolean InsertIntoFaborite(Item_Parameters story){


        ContentValues values=new ContentValues();
        values.put(DB_class.TITLE,story.getTitel());
        values.put(DB_class.STORY_TEXT,story.getText_story());
        long res=database.insert(DB_class.TABLE_FABORITE,null,values);
   return res!=-1;}
}
