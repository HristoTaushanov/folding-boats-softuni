package bg.softuni.boats.model.web;

import bg.softuni.boats.model.dto.UserProfileEditDTO;
import bg.softuni.boats.model.view.UserViewModel;
import bg.softuni.boats.model.view.UserWithRoleViewModel;
import bg.softuni.boats.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static int currentPageOn;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view-profile")
    public String viewProfile(Model model, Principal principal) {
        if(!model.containsAttribute("userViewModel")){
            model.addAttribute("userViewModel", new UserViewModel());
        }
        UserViewModel currentUser = userService.getCurrentUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "user-profile-view";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user-profile-edit";
    }

    @PostMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable("id") Long id,
                              @Valid UserProfileEditDTO userProfileEditDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileEditDTO", userProfileEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditDTO", bindingResult);
            return "redirect:/users/profile/edit";
        }
        userProfileEditDTO.setId(id);
        userService.updateUserProfile(userProfileEditDTO);

        return "redirect:/users/view-profile";
    }

    @GetMapping("/all")
    public String getAllPages(Model model) {
        return getOnePage(model, 1);
    }

    @Transactional
    @DeleteMapping("/deactivate/{id}")
    public String deActivate(@PathVariable("id") Long id) {
        userService.deactivate(id);
        return "redirect:/users/all";
    }

    @Transactional
    @PatchMapping("/activate/{id}")
    public String activate(@PathVariable("id") Long id) {
        userService.activate(id);
        return "redirect:/users/all";
    }

    @GetMapping("/all/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<UserWithRoleViewModel> page = userService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        Long totalUsers = page.getTotalElements();
        List<UserWithRoleViewModel> users = page.getContent();
        currentPageOn = currentPage;
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("users", users);

        return "admin-users-all";
    }

    @PostMapping("/promote/{id}")
    public String promoteUser(@PathVariable("id") Long id) {
        userService.promoteUser(id);
        return "redirect:/users/all/" + currentPageOn;
    }

    @PostMapping("/demote/{id}")
    public String demoteUser(@PathVariable("id") Long id) {
        userService.demoteUser(id);
        return "redirect:/users/all/" + currentPageOn;
    }
}
