package com.samar.quizapp.model;

public class Response {
    private Integer id;
    private String response;

    public Response() {
    }
    public Response(String response, Integer id) {
        this.response = response;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
