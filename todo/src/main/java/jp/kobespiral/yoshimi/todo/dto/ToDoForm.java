package jp.kobespiral.yoshimi.todo.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import jp.kobespiral.yoshimi.todo.entity.ToDo;
import lombok.Data;

@Data
public class ToDoForm {
    @Size(min = 1, max = 32)
    String title;

    public ToDo toEntity(String mid) {
        Date date = new Date();

        ToDo t = new ToDo(null, title, mid, null, false, date, null);
        return t;
    }
}
