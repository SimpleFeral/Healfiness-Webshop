package com.healfiness.backend.core.domain.dto.exceptions;

public class UpdateFailureException extends RuntimeException {
    public UpdateFailureException(String message) {
        super(message);
    }

    public UpdateFailureException(
            String failedToUpdateOrder,
            Throwable cause
    ) {
        super(failedToUpdateOrder, cause);
    }
}
