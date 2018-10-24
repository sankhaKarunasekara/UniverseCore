package lk.universe.core.exception;

import java.util.Arrays;
import java.util.List;

public class ApiException extends RuntimeException{

    private Integer status;
    private String message;
    private List<String> errors;

    public ApiException(Integer status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiException(Integer status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
