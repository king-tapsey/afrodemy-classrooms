package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumVoteType;

@Data
@Builder
public class ForumVoteRequest {
    private String voterUsername;
    private ForumVoteType voteType;
    private Long questionId;
    private Long answerId;
}
