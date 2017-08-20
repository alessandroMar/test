package com.test.thejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageItems implements Serializable {

  private static final long serialVersionUID = 1024215780157657318L;
  private String type;

  private String date;
  private List<Tag> tags;
  private PrimaryImage primary_image;
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

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public PrimaryImage getPrimary_image() {
    return primary_image;
  }

  public void setPrimary_image(PrimaryImage primary_image) {
    this.primary_image = primary_image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
