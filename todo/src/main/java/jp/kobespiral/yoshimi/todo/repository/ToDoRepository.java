package jp.kobespiral.yoshimi.todo.repository;

import org.springframework.stereotype.Repository;

import jp.kobespiral.yoshimi.todo.entity.ToDo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    List<ToDo> findByMidAndDone(String mid, Boolean done);

    List<ToDo> findByDone(Boolean done);

    List<ToDo> findByMid(String mid);

    List<ToDo> findAll();

}
