package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.TableModel;
import com.restaurant.restaurant_web.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public String getAllTables(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "3") int size,
                               Model model) {
        List<TableModel> tables = tableService.findAllTable(page, size);
        int totalTables = tableService.countTables();
        int totalPages = (int) Math.ceil((double) totalTables / size);
        model.addAttribute("tables", tables);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("table", new TableModel());
        return "tableList";
    }

    @PostMapping("/tables/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String addTable(@Valid @ModelAttribute("table") TableModel table, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("tables", tableService.findAllTable(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) tableService.countTables() / 3));
            return "tableList";
        }
        tableService.addTable(table);
        return "redirect:/tables";
    }

    @PostMapping("/tables/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String updateTable(@ModelAttribute("table") TableModel table) {
        tableService.updateTable(table);
        return "redirect:/tables";
    }

    @PostMapping("/tables/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String deleteTable(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            tableService.deleteTablePhysically(id);
        } else {
            tableService.deleteTableLogically(id);
        }
        return "redirect:/tables";
    }

    @GetMapping("/tables/{id}")
    public String getIdTables(@PathVariable("id") Long id, Model model){
        model.addAttribute("table", tableService.findTableById(id));
        return "tableList";
    }

}
