package com.test.thejournal.model;

import java.io.Serializable;

public class FeedResponse implements Serializable {

  private Response response;

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }
}
