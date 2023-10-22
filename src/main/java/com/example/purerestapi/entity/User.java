package com.example.purerestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private String id;
  private String firstname;
  private String surname;
  @JsonIgnore
  private List<Note> notes;
}
