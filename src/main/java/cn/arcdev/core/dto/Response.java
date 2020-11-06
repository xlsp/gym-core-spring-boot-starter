package cn.arcdev.core.dto;

/**
 * System common response.
 *
 * @param <T> data type
 * @author Kraken
 */
public class Response<T> {
    private int status;
    private String message;
    private T data;

    public Response() {
        this.status = 0;
    }

    public Response(T data) {
        this.status = 0;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Response<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
