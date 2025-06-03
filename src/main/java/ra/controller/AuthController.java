package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.entity.Customer;
import ra.service.AuthService;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("register")
    public String registerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("register")
    public String registerSubmit(@ModelAttribute("customer") Customer customer,
                                 RedirectAttributes redirectAttributes) {
        // Gọi service để lưu dữ liệu
        authService.save(customer);

        // Gửi thông báo thành công về cho view
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công!");
        return "redirect:/register";
    }

}
