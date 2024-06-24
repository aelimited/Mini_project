package fcbe7.bemini.domain.rating.response;

public record CommentResponse(
        Long id,
        Double score,
        String comment
) {
}
