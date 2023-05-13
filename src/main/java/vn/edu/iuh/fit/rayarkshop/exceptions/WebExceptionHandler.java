package vn.edu.iuh.fit.rayarkshop.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", 404);
        modelAndView.addObject("errorTitle", "KHÔNG TÌM THẤY TÀI NGUYÊN");
        modelAndView.addObject("errorMessage", "Không tìm thấy tài nguyên được yêu cầu. Tài nguyên đã bị xóa hoặc bạn không có quyền truy cập");

        modelAndView.setViewName("error");

        return modelAndView;
    }

    @ExceptionHandler(InternalServerException.class)
    public ModelAndView handleInternalServerException() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", 500);
        modelAndView.addObject("errorTitle", "SERVER ERROR");
        modelAndView.addObject("errorMessage", "Cố gắng tải lại trang và thử lại. Nếu vấn đề vẫn không được giải quyết vui lòng liên hệ với quản trị viên.");

        modelAndView.setViewName("error");

        return modelAndView;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", 403);
        modelAndView.addObject("errorTitle", "ACCESS DENIED");
        modelAndView.addObject("errorMessage", "Bạn không có quyền truy cập vào tài nguyên này. Vui lòng đăng nhập bằng tài khoản có quyền và thử lại.");

        modelAndView.setViewName("error");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", "???");
        modelAndView.addObject("errorTitle", "ĐÃ CÓ LỖI XẢY RA");
        modelAndView.addObject("errorMessage", "Vui lòng thử lại sau");

        modelAndView.setViewName("error");

        return modelAndView;
    }

}
