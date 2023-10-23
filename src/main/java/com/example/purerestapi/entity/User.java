package com.example.purerestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private String id;
  private String firstname;
  private String surname;
  @JsonIgnore
  @Setter(AccessLevel.NONE)
  private List<Note> notes;

  public void addNote(Note note) {
    if (notes == null) {
      notes = new ArrayList<>();
    }
    notes.add(note);
  }

  public List<Note> getNotes() {
    return Collections.unmodifiableList(notes);
  }

}
