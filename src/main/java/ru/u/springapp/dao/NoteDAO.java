package ru.u.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ru.u.springapp.models.Note;

import java.util.List;
import java.util.Optional;

@Component
public class NoteDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Note> index() {
        return jdbcTemplate.query("SELECT * FROM Note", new BeanPropertyRowMapper<>(Note.class));
    }


    public Optional<Note> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Note WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Note.class)).stream().findAny();
    }

    public void save(Note note) {
        jdbcTemplate.update("INSERT INTO Note (title, description) VALUES (?, ?)", note.getTitle(), note.getDescription());
    }

    public void update(int id, Note note) {
        jdbcTemplate.update("UPDATE Note SET title = ?, description = ? WHERE id = ?", note.getTitle(), note.getDescription(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Note WHERE id = ?", id);
    }

}
