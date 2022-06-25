package jp.kobespiral.yoshimi.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobespiral.yoshimi.todo.dto.LoginForm;
import jp.kobespiral.yoshimi.todo.dto.ToDoForm;
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
            ToDoForm tform = new ToDoForm();
            tform.setMid(mid);
            model.addAttribute("ToDoForm", tform);

            return "todolist";
        } else {
            return "login";
        }
    }

    @GetMapping("/todolist")
    String showTodoList(@ModelAttribute(name = "ToDoForm") ToDoForm form, Model model) {
        return "todolist";
    }

    @PostMapping("/addtodo")
    String addToDo(@ModelAttribute(name = "ToDoForm") ToDoForm form, Model model) {
        tService.createToDo(form);
        form.setTitle("");
        return "todolist";
    }
}