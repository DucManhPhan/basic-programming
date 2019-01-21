/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.ex_springboot.controller;

import com.manhpd.ex_springboot.domain.Contact;
import com.manhpd.ex_springboot.service.ContactService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author manh.phanduc
 */
@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    
    @GetMapping("/contact")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "list";
    }
    
    @GetMapping("/contact/create")
    public String create(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }
    
    @PostMapping("/contact/save")
    public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        
        contactService.save(contact);
        redirect.addFlashAttribute("success", "Saved contact successfully!");
        return "redirect:/contact";
    }
    
    @GetMapping("/contact/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", contactService.findOne(id));
        return "form";
    }
    
    @GetMapping("/contact/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        contactService.delete(id);
        redirect.addFlashAttribute("success", "Deleted contact successfully");
        return "redirect:/contact";
    }
}
