package com.j23.server.services.restaurant.qna;

import com.j23.server.controllers.exception.ConflictException;
import com.j23.server.models.qna.QnA;
import com.j23.server.repos.qna.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

  private final QnaRepository qnaRepository;

  public QnA addQna(QnA qnA) {
    if (qnaRepository.existsByQuestion(qnA.getQuestion())) {
      throw new ConflictException("Question already exists");
    }

    QnA lastQna = qnaRepository.findFirstByOrderByNumberDesc();
    if (lastQna != null) {
      System.out.println("The last number is : " + lastQna);
      qnA.setNumber(lastQna.getNumber() + 1);
    } else {
      qnA.setNumber(1);
    }
    return qnaRepository.save(qnA);
  }

  public QnA updateQna(QnA qnA) {
    if (qnaRepository.existsByQuestionAndIdIsNot(qnA.getQuestion(), qnA.getId())) {
      throw new ConflictException("Question already exists");
    }
    return qnaRepository.save(qnA);
  }

  public Page<QnA> findAllQna(String search, Integer page, Integer size) {
    if (search == null) {
      return qnaRepository.findAll(PageRequest.of(page, size));
    }
    return qnaRepository.findAllByQuestionContaining(search, PageRequest.of(page, size));
  }

  public void deleteQna(UUID id) {
    if (!qnaRepository.existsById(id)) {
      throw new ResponseStatusException(NOT_FOUND, "Qna not found");
    }
    qnaRepository.deleteById(id);
  }
}
