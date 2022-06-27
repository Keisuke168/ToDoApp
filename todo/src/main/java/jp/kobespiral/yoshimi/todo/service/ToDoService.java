package jp.kobespiral.yoshimi.todo.service;

import java.util.List;
import java.util.Date;
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

    public ToDo createToDo(ToDoForm form, String mid) {
        ToDo t = form.toEntity(mid);
        return tRepo.save(t);
    }

    public ToDo getToDo(Long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
                () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such seq exists"));
        return t;
    }

    public List<ToDo> getToDoList(String mid) {
        List<ToDo> t = tRepo.findByMidAndDone(mid, false);
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

    public void doneToDo(Long seq) {
        ToDo t = getToDo(seq);
        t.setDone(true);
        t.setDoneAt(new Date());
        tRepo.save(t);

    }

}
