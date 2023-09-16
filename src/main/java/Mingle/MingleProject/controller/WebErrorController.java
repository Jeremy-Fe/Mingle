package Mingle.MingleProject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements getErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }

    @Override
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        System.out.println("status"+status);
        if(status != null){
            int statusCode = Integer.valueOf(status.toString());
            System.out.println("statusCode = "+statusCode);
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                System.out.println("error = 404 ");
                return "error/404";
            }else if (statusCode == 500) {
                System.out.println("error = 500 ");
                return "error/500";
            }
        }

        return "error/404";
    }

}