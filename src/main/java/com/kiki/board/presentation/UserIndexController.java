package com.kiki.board.presentation;

import com.kiki.board.application.UserService;
import com.kiki.board.application.dto.UserDto;
import com.kiki.board.application.validator.CustomValidators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class UserIndexController {

    private final UserService userService;
    private final HttpSession httpSession;

    private final CustomValidators.EmailValidator EmailValidator;
    private final CustomValidators.NicknameValidator NicknameValidator;
    private final CustomValidators.UsernameValidator UsernameValidator;


    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator);
        binder.addValidators(NicknameValidator);
        binder.addValidators(UsernameValidator);
    }

    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.Request dto, Errors errors, Model model) {

        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("dto", dto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            /* 회원가입 페이지로 다시 리턴 */
            return "/user/user-join";
        }


        userService.join(dto);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/user-login";
    }

    /* 회원정보 수정 */
    @GetMapping("/modify")
    public String modify(Model model) {
        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userDto", user);

        }
        return "/user/user-modify";
    }
}


