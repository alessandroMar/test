package com.test.thejournal.model;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

  private List<PageItems> page_items;

  public List<PageItems> getPage_items() {
    return page_items;
  }

  public void setPage_items(List<PageItems> page_items) {
    this.page_items = page_items;
  }
}
