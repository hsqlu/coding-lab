package com.hsqlu.coding.etl.ee;

public class ETLClusterException extends RuntimeException {

    private static final long serialVersionUID = 5747774224163576838L;

    public ETLClusterException() {
        super();
    }

    public ETLClusterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ETLClusterException(String message) {
        super(message);
    }

    public ETLClusterException(Throwable cause) {
        super(cause);
    }

}
