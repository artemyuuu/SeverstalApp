package ru.u.springapp.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Note {
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    private String title;

    @NotEmpty(message = "Описание не должно быть пустым")
    @Size(max = 5000, message = "Максимальная длина 5000 символов")
    private String description;

    public Note() {
        super();
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
