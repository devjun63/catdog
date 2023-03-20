package kr.co.catdog.controller;

import kr.co.catdog.dto.UserDTO;
import kr.co.catdog.service.ShopService;
import kr.co.catdog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final ShopService shopService;

    @GetMapping("/login")
    String signinForm() {
        return "sign-in";
    }

    @PostMapping("/login")
    String signin(UserDTO userDTO, HttpServletRequest request) {
        UserDTO user = userService.findById(userDTO.getUser_id());
//        id없을때 정보없다고알려줘

        if (user.getUser_pw().equals(userDTO.getUser_pw())) {
            HttpSession session = request.getSession();
            session.setAttribute("session_id", user.getUser_id());
            session.setAttribute("session_name",user.getUser_name());
            int cart = shopService.findById_Cart(user.getUser_id()).size();
            session.setAttribute("session_cart",cart);

            log.info(String.valueOf(session.getAttribute("session_cart")));
            log.info(String.valueOf(cart));
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if(session!=null){
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    String signupForm() {
        return "sign-up";
    }

    @PostMapping("/signup")
    String signup(UserDTO userDTO) {
        int result = userService.insert(userDTO);
        log.info(String.valueOf(result));
        return "redirect:/login";
    }


}
