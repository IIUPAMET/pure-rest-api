package com.example.purerestapi.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.purerestapi.controller.UserController;
import com.example.purerestapi.entity.Note;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class NoteAssembler implements RepresentationModelAssembler<Note, EntityModel<Note>> {

  @Override
  public EntityModel<Note> toModel(Note note) {
    return EntityModel.of(
        note,
        linkTo(methodOn(UserController.class).getUserNotes(note.getUserUuid())).withSelfRel(),
        linkTo(methodOn(UserController.class).getUser(note.getUserUuid())).withRel("user")
    );
  }
}
