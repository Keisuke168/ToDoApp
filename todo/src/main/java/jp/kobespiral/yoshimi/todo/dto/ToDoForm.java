package jp.kobespiral.yoshimi.todo.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;

import jp.kobespiral.yoshimi.todo.entity.ToDo;
import lombok.Data;

@Data
public class ToDoForm {
    @Pattern(regexp = "{1,64}")
    String title;

    public ToDo toEntity(String mid) {
        Date date = new Date();

        ToDo t = new ToDo(null, title, mid, null, false, date, null);
        return t;
    }
}
