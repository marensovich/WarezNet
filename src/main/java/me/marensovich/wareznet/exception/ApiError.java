package me.marensovich.wareznet.exception;

import java.time.LocalDateTime;

/**
 * Represents a structured API error response.
 *
 * <p>Returned by {@link com.marensovich.eljur.exceptions.GlobalExceptionHandler}
 * when exceptions are thrown in the application.</p>
 *
 * @param status    HTTP status code
 * @param error     error name (e.g. "Not Found", "Unauthorized")
 * @param message   error message
 * @param path      request path where the error occurred
 * @param timestamp time of error occurrence
 *
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
public record ApiError(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp
) { }