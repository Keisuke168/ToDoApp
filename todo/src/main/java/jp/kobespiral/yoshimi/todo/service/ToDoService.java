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

    // TODOを登録する
    public ToDo createToDo(ToDoForm form, String mid, String name) {
        ToDo t = form.toEntity(mid);
        t.setName(name);
        return tRepo.save(t);
    }

    // IDで指定してTODOを取得する
    public ToDo getToDo(Long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
                () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such seq exists"));
        return t;
    }

    // midのTODOリストを取得する
    public List<ToDo> getToDoList(String mid) {
        List<ToDo> t = tRepo.findByMidAndDone(mid, false);
        return t;
    }

    // midのDONEリストを取得する
    public List<ToDo> getDoneList(String mid) {
        List<ToDo> t = tRepo.findByMidAndDone(mid, true);
        return t;
    }

    // 全員のTODOリスト取得
    public List<ToDo> getToDoList() {
        List<ToDo> t = tRepo.findByDone(false);
        return t;
    }

    // 全員のDONEリスト取得
    public List<ToDo> getDoneList() {
        List<ToDo> t = tRepo.findByDone(true);
        return t;
    }

    // IDで指定したTODOをDONEに更新する
    public void doneToDo(Long seq) {
        ToDo t = getToDo(seq);
        t.setDone(true);
        t.setDoneAt(new Date());
        tRepo.save(t);
    }

}
