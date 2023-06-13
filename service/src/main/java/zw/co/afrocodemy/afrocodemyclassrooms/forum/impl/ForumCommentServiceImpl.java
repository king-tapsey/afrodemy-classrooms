package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumCommentService;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumCommentRequest;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {
    @Override
    public ResponseEntity<?> getQuestionComments(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAnswerComments(Long answerId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllByUserAccount(String username) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(ForumCommentRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ForumCommentRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id, String username) {
        return null;
    }
}
