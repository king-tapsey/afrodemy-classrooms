package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumCommentRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumCommentServiceImpl implements ForumCommentService {
    private final DtoMapper mapper;
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumQuestionCommentRepository forumQuestionCommentRepository;
    private final ForumAnswerCommentRepository forumAnswerCommentRepository;
    private final ForumCommentRepository forumCommentRepository;

    @Override
    public ResponseEntity<?> getQuestionComments(Long questionId) {
        Optional<ForumQuestion> question = forumQuestionRepository.findById(questionId);
        if(question.isEmpty()){
            throw new InvalidRequestException("Could not find Forum Question with id: " + questionId);
        }
        List<ForumQuestionComment> questionComments = forumQuestionCommentRepository.findAllByQuestion(question.get());
        if(questionComments.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(questionComments);
    }

    @Override
    public ResponseEntity<?> getAnswerComments(Long answerId) {
        Optional<ForumAnswer> answer = forumAnswerRepository.findById(answerId);
        if(answer.isEmpty()){
            throw new InvalidRequestException("Could not find Forum Answer with id: " + answerId);
        }
        List<ForumAnswerComment> answerComments = forumAnswerCommentRepository.findAllByAnswer(answer.get());
        if(answerComments.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(answerComments);
    }

    @Override
    public ResponseEntity<?> getAllByUserAccount(String username) {
        List<ForumComment> comments = forumCommentRepository.findAllByCreatorUsername(username);
        if(comments.isEmpty()) {
            return null;
        }
        List<?> commentsResponse = comments.stream().map(comment -> {
            if(comment instanceof ForumQuestionComment forumQuestionComment){
                return forumQuestionComment;
            }
            if(comment instanceof ForumAnswerComment forumAnswerComment){
                return forumAnswerComment;
            }
            return comment;
        }).toList();

        return ResponseEntity.ok(commentsResponse);
    }

    @Override
    public ResponseEntity<?> create(ForumCommentRequest request) {
        if(request.getAssociatedUsername() == null){
            throw new InvalidRequestException("Comment Creator Username cannot be null.");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Comment Text cannot be null");
        }
        if(request.getQuestionId() == null ^ request.getAnswerId() == null){
            throw new InvalidRequestException("Question Id and Answer Id cannot be both null or non null.");
        }
        if(request.getQuestionId() != null){
            ForumQuestionComment comment = mapper.requestToForumQuestionCommentCreate(request);
            return ResponseEntity.ok(forumCommentRepository.save(comment));
        }
        ForumAnswerComment comment = mapper.requestToForumAnswerCommentCreate(request);
        return ResponseEntity.ok(forumCommentRepository.save(comment));
    }

    @Override
    public ResponseEntity<?> update(ForumCommentRequest request) {
        String username = request.getAssociatedUsername(); //TODO: use with spring security to enforce method security

        if(request.getId() == null){
            throw new InvalidRequestException("Comment Id cannot be empty");
        }
        if(request.getAssociatedUsername() == null){
            throw new InvalidRequestException("Username cannot be empty");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Comment Text cannot be empty");
        }

        ForumComment comment = mapper.requestToForumCommentUpdate(request);
        return ResponseEntity.ok(forumCommentRepository.save(comment));
    }

    @Override
    public ResponseEntity<?> delete(Long commentId, String username) {
        ForumComment comment = forumCommentRepository.findById(commentId).orElse(null);
        if(comment == null){
            throw new InvalidRequestException("Could not find comment with id: " + commentId);
        }
        forumCommentRepository.delete(comment);
        return ResponseEntity.ok("Successfully deleted comment with id: " + commentId);
    }
}
