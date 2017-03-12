package com.hello2mao.focus.model.bean;

public class ResultResponse<T> {

    private String has_more;
    private String message;
    private T data;

    public ResultResponse(String more, String _message, T result) {
        has_more = more;
        message = _message;
        data = result;
    }

    public String getHas_more() {
        return has_more;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
