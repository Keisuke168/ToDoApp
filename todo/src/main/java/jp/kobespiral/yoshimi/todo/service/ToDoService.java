package jp.kobespiral.yoshimi.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobespiral.yoshimi.todo.dto.ToDoForm;
import jp.kobespiral.yoshimi.todo.entity.ToDo;
import jp.kobespiral.yoshimi.todo.repository.ToDoRepository;
import jp.kobespiral.yoshimi.todo.exception.ToDoAppException;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository tRepo;

    public ToDo createToDo(ToDoForm form) {
        ToDo t = form.toEntity();
        return tRepo.save(t);
    }

    public ToDo getToDo(Long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
                () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such seq exists"));
        return t;
    }

    public List<ToDo> getToDoList(String mid) {
        List<ToDo> t = tRepo.findByMid(mid);
        return t;
    }

    public List<ToDo> getDoneList(String mid) {
        List<ToDo> t = tRepo.findByMidAndDone(mid, true);
        return t;
    }

    public List<ToDo> getToDoList() {
        List<ToDo> t = tRepo.findAll();
        return t;
    }

    public List<ToDo> getDoneList() {
        List<ToDo> t = tRepo.findByDone(true);
        return t;
    }

}
