package com.test.thejournal.model;

import java.io.Serializable;

public class Tag implements Serializable {

  private String slug;

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }
}
