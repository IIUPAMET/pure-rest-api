package com.example.purerestapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.purerestapi.controller.assembler.NoteAssembler;
import com.example.purerestapi.controller.assembler.UserAssembler;
import com.example.purerestapi.entity.Note;
import com.example.purerestapi.entity.User;
import com.example.purerestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ResponseBody
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserAssembler userAssembler;
  private final NoteAssembler noteAssembler;

  @GetMapping
  public CollectionModel<EntityModel<User>> getAll() {
    var users = userService.getAll().stream()
        .map(userAssembler::toModel)
        .toList();
    return CollectionModel.of(
        users,
        linkTo(methodOn(UserController.class).getAll()).withSelfRel()
    );
  }

  @GetMapping("/{id}")
  public EntityModel<User> getUser(@PathVariable String id) {
    var user = userService.getUser(id);

    return userAssembler.toModel(user);
  }

  @PostMapping
  public ResponseEntity<EntityModel<User>> addUser(@RequestBody User user) {
    userService.addUser(user);
    EntityModel<User> entityModel = userAssembler.toModel(user);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
  }

  @PostMapping("/{id}/notes")
  public EntityModel<User> addNote(@PathVariable(name = "id") String userId, @RequestBody Note note) {
    var user = userService.addNote(userId, note);

    return userAssembler.toModel(user);
  }

  @GetMapping("/{id}/notes")
  public CollectionModel<EntityModel<Note>> getUserNotes(@PathVariable(name = "id") String userId) {
    var notes = userService.getAllNotes(userId).stream()
        .map(noteAssembler::toModel)
        .toList();

    return CollectionModel.of(
        notes,
        linkTo(methodOn(UserController.class).getUserNotes(userId)).withSelfRel()
    );
  }
}
