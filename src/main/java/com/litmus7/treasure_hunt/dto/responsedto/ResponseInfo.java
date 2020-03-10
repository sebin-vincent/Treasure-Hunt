package com.litmus7.treasure_hunt.dto.responsedto;

public class ResponseInfo {

    private int status;
    private String message;
    private Object payload;

    public ResponseInfo(int status, String message) {
        this.status=status;
        this.message=message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
