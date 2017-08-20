package com.test.thejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

  private static final long serialVersionUID = 4346787720541355013L;
  private List<PageItems> page_items;

  public List<PageItems> getPage_items() {
    return page_items;
  }

  public void setPage_items(List<PageItems> page_items) {
    this.page_items = page_items;
  }
}
