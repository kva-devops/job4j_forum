package ru.job4j.forum.control;

//@Controller
public class RegControl {

//    private final UserMem users;
//
//    private final AuthorityMem authorities;
//
//    public RegControl(UserMem users, AuthorityMem authorities) {
//        this.users = users;
//        this.authorities = authorities;
//    }
//
//    @GetMapping("/reg")
//    public String regPage() {
//        return "reg";
//    }
//
//    @PostMapping("/reg")
//    public String regSave(@ModelAttribute User user, Model model) {
////        user.setEnabled(true);
////        user.setPassword(user.getPassword());
//        user.setAuthority(authorities.findByAuthority("USER"));
//        User buff = users.createUser(user);
//        String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
//        if (buff == null) {
//            model.addAttribute("errorMessage", errorMessage);
//            return "reg";
//        }
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginCheck(@RequestParam("email") String email,
//                             @RequestParam("password") String password,
//                             Model model) {
//        User rsl = users.findUserByEmail(email);
//        if (password.equals(rsl.getPassword())) {
//            rsl.setEnabled(true);
//            model.addAttribute("user", rsl);
//            return "index";
//        }
//        return "redirect:/login";
//    }

}
