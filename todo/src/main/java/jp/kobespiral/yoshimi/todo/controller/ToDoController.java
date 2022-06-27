package jp.kobespiral.yoshimi.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobespiral.yoshimi.todo.dto.LoginForm;
import jp.kobespiral.yoshimi.todo.dto.ToDoForm;
import jp.kobespiral.yoshimi.todo.entity.Member;
import jp.kobespiral.yoshimi.todo.entity.ToDo;
import jp.kobespiral.yoshimi.todo.service.MemberService;
import jp.kobespiral.yoshimi.todo.service.ToDoService;

@Controller
public class ToDoController {
    @Autowired
    ToDoService tService;

    @Autowired
    MemberService mService;

    /**
     * 管理者用・ユーザ登録ページ HTTP-GET /admin/register
     * 
     * @param model
     * @return
     */
    @GetMapping("/login")
    String showLoginForm(Model model) {
        LoginForm form = new LoginForm();
        model.addAttribute("LoginForm", form);

        return "login";
    }

    @PostMapping("/check")
    String checkUserForm(@ModelAttribute(name = "LoginForm") LoginForm form, Model model) {
        String mid = form.getMid();

        if (mService.checkExist(mid)) {
            return "redirect:/todolist/" + mid;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/todolist/{mid}")
    String showTodoList(@PathVariable String mid, Model model) {
        List<ToDo> todolist = tService.getToDoList(mid);
        List<ToDo> donelist = tService.getDoneList(mid);
        ToDoForm tform = new ToDoForm();
        model.addAttribute("mid", mid);
        model.addAttribute("todolist", todolist);
        model.addAttribute("donelist", donelist);
        model.addAttribute("ToDoForm", tform);
        return "todolist";
    }

    @PostMapping("/todolist/{mid}/addtodo")
    String addToDo(@ModelAttribute(name = "ToDoForm") ToDoForm tform, @PathVariable String mid, Model model) {
        Member m = mService.getMember(mid);
        tService.createToDo(tform, mid, m.getName());
        return "redirect:/todolist/" + mid;
    }

    @GetMapping("/todolist/{mid}/donetodo/{seq_str}")
    String doneToDo(
            @PathVariable String mid,
            @PathVariable String seq_str,
            Model model) {
        Long seq = Long.parseLong(seq_str);
        tService.doneToDo(seq);
        return ("redirect:/todolist/" + mid);
    }
}