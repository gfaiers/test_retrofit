package com.pavers.testretrofit;

public class TokenResponse {
    private String message;
    private String atFunction;
    private String readableMessage;
    private int binNumber;
    private String locationName;
    private int available;
    private int colourAvailable;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAtFunction() {
        return atFunction;
    }

    public void setAtFunction(String atFunction) {
        this.atFunction = atFunction;
    }

    public String getReadableMessage() {
        return readableMessage;
    }

    public void setReadableMessage(String readableMessage) {
        this.readableMessage = readableMessage;
    }

    public int getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(int binNumber) {
        this.binNumber = binNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getColourAvailable() {
        return colourAvailable;
    }

    public void setColourAvailable(int colourAvailable) {
        this.colourAvailable = colourAvailable;
    }
}
