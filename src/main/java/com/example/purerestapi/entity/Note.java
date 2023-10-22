package com.example.purerestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Note {

  @JsonIgnore
  private String userUuid;
  private String subject;
  private String text;
}
