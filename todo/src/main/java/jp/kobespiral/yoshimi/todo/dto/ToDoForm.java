package jp.kobespiral.yoshimi.todo.dto;

import java.util.Date;

import jp.kobespiral.yoshimi.todo.entity.ToDo;
import lombok.Data;

@Data
public class ToDoForm {
    String title;

    public ToDo toEntity(String mid) {
        Date date = new Date();

        ToDo t = new ToDo(null, title, mid, false, date, null);
        return t;
    }
}
