package com.example.SpringWebMVC.controller;


import com.example.SpringWebMVC.model.User;
import com.example.SpringWebMVC.services.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
public class PageController {
    public static Faker faker = new Faker();
    @Autowired
    UserService userService;
    @GetMapping("/home")
    public @ResponseBody String getHomepage(){
        return "Prima pagina MVC SPring";
    }
    @GetMapping("/home/params")
    public @ResponseBody String getHomepagewithParams(@RequestParam String name,@RequestParam String lastname){
        //http://localhost:8080/home/params?name=Mario
        return "Prima pagina MVC SPring con params di tiopo Query String " + name + " " + lastname;
    }

    @GetMapping("/home/params/{name}")
    public @ResponseBody String getFromPageWithPathParams(@PathVariable String name){
        return "Prima pagina MVC SPring con params di tiopo PathVariable " + name ;
    }

    @GetMapping("/home/params/{name}/{lastname}")
    public @ResponseBody String getFromPageWithPathParams(@PathVariable String name,@PathVariable String lastname){
        return "<h2>Prima pagina MVC SPring con params di tiopo</h2> <h3>PathVariable " + name+ ""+lastname+"</h3>";
    }
    @GetMapping("/page1/{name}/{lastname}")
    public String getPageThymeLeft(Map<String, Object> model, @PathVariable String name, @PathVariable String lastname){
        model.put("firstname", name);
        model.put("lastname", lastname);
        return "pageThymeLeft";

    }
    @GetMapping("/page2/{name}/{lastname}")
    public String getPageThymeLeft(Model model, @PathVariable String name, @PathVariable String lastname){
        model.addAttribute("firstname", name);
        model.addAttribute("lastname", lastname);
        return "pageThymeLeft";

    }
    @GetMapping("/page3/{name}/{lastname}")
    public String getPageThymeLeft(ModelMap model, @PathVariable String name, @PathVariable String lastname){
        model.addAttribute("firstname", name);
        model.addAttribute("lastname", lastname);
        return "pageThymeLeft";

    }

    @GetMapping("/page4/{name}/{lastname}")
    public ModelAndView getPageThymeLeft(@PathVariable String name, @PathVariable String lastname){
        //ModelAndView model = new ModelAndView("pageThymeLeft");

        String page="pageThymeLeft";
        ModelAndView model=new ModelAndView(page);
        model.addObject("firstname", name);
        model.addObject("lastname", lastname);
        return model;

    }
    @GetMapping("/login")
    public String getLoginPage(

    ) {

        return "login";
    }

    @GetMapping("/")
    public String home(

    ) {

        return "redirect:/userslist";
    }

    @GetMapping("/gestione-login")
    public @ResponseBody String gestioneLoginGet(@RequestParam String loginemail,@RequestParam String loginpass) {

        return "PAGE LOGIN CONTROLLER" +loginemail+loginpass;
    }
    @PostMapping("/gestione-login")
    public @ResponseBody String gestioneLoginPost(@RequestParam String loginemail,@RequestParam String loginpass) {
User user=userService.checkLogin(loginemail,loginpass);
if(user!=null){return "Controller"+ user.getFirstname()+""+user.getLastname();}

        //return "PAGE LOGIN CONTROLLER" +loginemail+loginpass;

        else{
    return "ERROR di login";
}
    }
    @GetMapping("/userslist")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        //return userService.getAllUsers().toString();
        return "userslist"; // Имя HTML-шаблона
    }

    @PostMapping("/registered")
    public  ModelMap gestioneRegisterPost(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password)  {

        ModelMap model =new ModelMap();
        User user=new User(firstname,lastname,email,password);
        model.addAttribute("firstname", firstname);
        model.addAttribute("lastname", lastname);
        model.addAttribute("email", email);
        if(user!=null)  {
            System.out.println();

                userService.registerUser(user);




            //return "redirect:/userslist";
            return model;

        }

        //return "PAGE LOGIN CONTROLLER" +loginemail+loginpass;

        else{
            model=new ModelMap("ERROR di registrazione.");
            //return "ERROR di registrazione.";
            return model;
        }

    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/userslist";
    }
    @GetMapping("/users/view/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", userService.getUserById(id));
        return "view-user";
    }
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model)  {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/users/edit")
    public String updateUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/userslist";
    }

    @GetMapping("/register")
    public String getRegisterPage(

    ) {

        return "register";
    }

}
