package com.cf.demo.service.threadpool.util;

/**
 * @author liweinan
 * @date 2020/8/6
 */
public class RejectedTaskException extends Exception{

    private static final long serialVersionUID = -1L;

    public RejectedTaskException() { }

    public RejectedTaskException(String message) {
        super(message);
    }

    public RejectedTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public RejectedTaskException(Throwable cause) {
        super(cause);
    }
}
