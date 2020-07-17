package com.chugunova.myproject.model;

public class Message {
    private String mesFrom;
    private String mesText;
    private String mesDateTime;

    public Message(String mesFrom, String mesText, String mesDateTime) {
        this.mesFrom = mesFrom;
        this.mesText = mesText;
        this.mesDateTime = mesDateTime;
    }

    public String getMesFrom() {
        return mesFrom;
    }

    public void setMesFrom(String mesFrom) {
        this.mesFrom = mesFrom;
    }

    public String getMesText() {
        return mesText;
    }

    public void setMesText(String mesText) {
        this.mesText = mesText;
    }

    public String getMesDateTime() {
        return mesDateTime;
    }

    public void setMesDateTime(String mesDateTime) {
        this.mesDateTime = mesDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mesFrom='" + mesFrom + '\'' +
                ", mesText='" + mesText + '\'' +
                ", mesDateTime='" + mesDateTime + '\'' +
                '}';
    }
}
