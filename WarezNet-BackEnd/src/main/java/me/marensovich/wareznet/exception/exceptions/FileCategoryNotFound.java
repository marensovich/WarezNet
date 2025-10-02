package me.marensovich.wareznet.exception.exceptions;

/**
 * Thrown when a requested file category is not found.
 *
 * <p>Used in file management and storage operations.</p>
 * @author marensovich
 * @version v.0.1
 * @since v.0.1
 */
public class FileCategoryNotFound extends RuntimeException {
    /**
     * Instantiates a new File not found exception.
     *
     * @param message the error message
     */
    public FileCategoryNotFound(String message) {
        super(message);
    }
}