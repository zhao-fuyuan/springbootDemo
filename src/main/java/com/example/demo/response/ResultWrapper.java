package com.example.demo.response;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class ResultWrapper<T> {
    private static final long serialVersionUID = -6326130226412646102L;
    static final String NO_SET_SUCCESS_ERROR = "must set success attribute!";
    private Boolean success;
    private T data;
    private String type;
    private String code;
    private List<String> messages;
    private String toast = "";
    private String traceid = "";
    private boolean dataEncrypt;

    public ResultWrapper() {
    }

    public ResultWrapper(boolean success, String type, String code, String message) {
        this.success = success;
        this.type = type;
        this.code = code;
        this.messages = new ArrayList();
        this.messages.add(message);
    }

    public ResultWrapper(T data) {
        this.success = true;
        this.data = data;
    }

    public ResultWrapper<T> setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResultWrapper<T> setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public ResultWrapper<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public ResultWrapper<T> setToast(String toast) {
        this.toast = toast;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public ResultWrapper<T> addMessage(String... messages) {
        if (this.messages == null) {
            this.messages = new ArrayList();
        }

        String[] var2 = messages;
        int var3 = messages.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String message = var2[var4];
            this.messages.add(message);
        }

        return this;
    }

    public ResultWrapper<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ResultWrapper<T> addMessage(List<String> messages) {
        if (this.messages == null) {
            this.messages = new ArrayList();
        }

        this.messages.addAll(messages);
        return this;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String formMessage(String delimiter) {
        StringBuilder sb = new StringBuilder();
        if (null != this.messages && !this.messages.isEmpty()) {
            for(int i = 0; i < this.messages.size() - 1; ++i) {
                String message = (String)this.messages.get(i);
                sb.append(message).append(delimiter);
            }

            sb.append((String)this.messages.get(this.messages.size() - 1));
        }

        return sb.toString();
    }

    public String getMessage() {
        return this.formMessage("\n");
    }

    public boolean isSuccess() {
        Assert.notNull(this.success, "must set success attribute!");
        return this.success;
    }

    public boolean isFailure() {
        return !this.isSuccess();
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getToast() {
        return this.toast;
    }

    public String getTraceid() {
        return this.traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public boolean isDataEncrypt() {
        return this.dataEncrypt;
    }

    public void setDataEncrypt(boolean dataEncrypt) {
        this.dataEncrypt = dataEncrypt;
    }
}
