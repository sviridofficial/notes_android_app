package com.example.training;

public class note {
    private String title;
    private String text;
    public note(String title,String text){
        this.title = title;
        this.text =  text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
