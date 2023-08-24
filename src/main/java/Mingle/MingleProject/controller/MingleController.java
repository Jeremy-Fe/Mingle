package Mingle.MingleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MingleController {
    @GetMapping("login*")
    public String login() {
        return "login";
    }

    @GetMapping("find_id*")
    public String find_id() { return "find_id"; }

    @GetMapping("find_pw*")
    public String find_pw() { return "find_pw"; }

    @GetMapping("login_all*")
    public String login_all() { return "login_all"; }

    @GetMapping("test*")
    public String test() { return "test"; }

    @GetMapping("Main_UnLogIn*")
    public String Main_UnLogIn() {
        return "Main_UnLogIn";
    }

    @GetMapping("myClass*")
    public String myClass() {
        return "myClass";
    }
}


