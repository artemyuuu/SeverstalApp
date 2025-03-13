package ru.u.springapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.u.springapp.dao.NoteDAO;
import ru.u.springapp.models.Note;

import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final NoteDAO noteDAO;

    @Autowired
    public NotesController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("notes", noteDAO.index());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Note> optionalNote = noteDAO.show(id);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            model.addAttribute("note", note);
            return "notes/show";
        } else {
            return "redirect:/notes";
        }
    }
    @GetMapping("/new")
    public String newNote(@ModelAttribute("note") Note note) {
        return "notes/new";
    }

    @PostMapping
    public String create(@ModelAttribute("note") @Valid Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "notes/new";
        }

        noteDAO.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Note> optionalNote = noteDAO.show(id);
        if (optionalNote.isPresent()) {
            model.addAttribute("note", optionalNote.get());
            return "notes/edit";
        } else {
            return "redirect:/notes";
        }
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("note") @Valid Note note, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "notes/edit";
        }

        noteDAO.update(id, note);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        noteDAO.delete(id);
        return "redirect:/notes";
    }

}
