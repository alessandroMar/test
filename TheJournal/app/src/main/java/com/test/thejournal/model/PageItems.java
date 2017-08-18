package com.test.thejournal.model;


public class PageItems {

  private String type;

  private String date;
  private PrimaryImage primary_mage;
  private String title;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public PrimaryImage getPrimary_mage() {
    return primary_mage;
  }

  public void setPrimary_mage(PrimaryImage primary_mage) {
    this.primary_mage = primary_mage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
