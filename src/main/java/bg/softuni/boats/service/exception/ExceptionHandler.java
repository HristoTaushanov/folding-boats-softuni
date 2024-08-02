package bg.softuni.boats.service.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "An unexpected error occurred. Please try again later.");
        return modelAndView;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BoatNotFoundException.class)
    public ModelAndView handleBoatNotFoundException(BoatNotFoundException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

}
