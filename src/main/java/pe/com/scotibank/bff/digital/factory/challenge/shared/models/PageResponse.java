package pe.com.scotibank.bff.digital.factory.challenge.shared.models;

import java.util.List;

public record PageResponse<T>(
        /* content. */
        List<T> content,
        /* page. */
        int page,
        /* size. */
        int size,
        /* total. */
        long total,
        /* totalPages. */
        int totalPages
) {
    public PageResponse(List<T> content, int page, int size, long total) {
        this(content, page, size, total, (int) Math.ceil((double) total / size));
    }
}
