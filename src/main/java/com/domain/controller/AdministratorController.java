package com.domain.controller;

import com.domain.domain.Administrator;
import com.domain.exception.RecordNotFoundException;
import com.domain.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    AdministratorService service;

    @RequestMapping
    public String getAllAdministrators(Model model)
    {
        List<Administrator> list = service.getAllAdministrators();
        model.addAttribute("administrators", list);
        return "list-administrator";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editAdministratorById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        if (id.isPresent()) {
            Administrator entity = service.getAdministratorById(id.get());
            model.addAttribute("administrator", entity);
        } else {
            model.addAttribute("administrator", new Administrator());
        }
        return "add-edit-administrator";
    }
    @RequestMapping(path = "/delete/{id}")
    public String deleteAdministratorById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        service.deleteAdministratorById(id);
        return "redirect:/";
    }
    @RequestMapping(path = "/createAdministrator", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Administrator administrator)
    {
        service.createOrUpdateAdministrator(administrator);
        return "redirect:/";
    }
}