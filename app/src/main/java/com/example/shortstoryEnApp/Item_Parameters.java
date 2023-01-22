package com.example.shortstoryEnApp;

public class Item_Parameters {
    private  int id;
    private String titel;
    private String text_story;

    public Item_Parameters(int id, String titel, String text_story) {
        this.id = id;
        this.titel = titel;
        this.text_story = text_story;
    }
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText_story() {
        return text_story;
    }

    public void setText_story(String text_story) {
        this.text_story = text_story;
    }

    public int getId() {
        return id;
    }
}
