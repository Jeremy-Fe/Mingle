package Mingle.MingleProject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public interface getErrorController extends ErrorController {
    String getErrorPath();

    @GetMapping("/error")
    String handleError(HttpServletRequest request);
}
