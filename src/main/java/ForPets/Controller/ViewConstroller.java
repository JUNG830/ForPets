package ForPets.Controller;

import ForPets.config.AdminAuthorize;
import ForPets.config.UserAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(value = "http://localhost:3000")
@Controller
@Slf4j
public class ViewConstroller {
    // http://localhost:8211/ 으로 접속 시

    /**
     * 메인 홈
     * @return
     */
    @GetMapping("/main")
    public ModelAndView Main() {
        log.warn("메인 view");
        ModelAndView mav = new ModelAndView();
        //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
        mav.setViewName("main");

        return mav;
    }

    /**
     * 로그인
     * @return
     */
    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView();
        log.warn("로그인 view");
        mav.setViewName("login");
        return mav;
    }

    /**
     * 회원가입
     * @return
     */
    @GetMapping("/signup")
    public ModelAndView SignUpPage() {
        log.warn("회원가입 view");
        ModelAndView mav = new ModelAndView();
        //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
        mav.setViewName("signUp");

        return mav;
    }

    @GetMapping("/setting/admin")
    @AdminAuthorize
    public String adminSettingPage() {
        return "admin_setting";
    }

    @GetMapping("/setting/user")
    @UserAuthorize
    public String userSettingPage() {
        return "user_setting";
    }

}
