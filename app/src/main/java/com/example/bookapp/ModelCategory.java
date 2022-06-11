package com.example.bookapp;


public class ModelCategory {
  private  String category,id,uid;
  private long timestamp;

  public ModelCategory(){

  }
    public ModelCategory(String category, String id, String uid, long timestamp) {
        this.category = category;
        this.id = id;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
