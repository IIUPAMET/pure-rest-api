package com.example.purerestapi.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.purerestapi.controller.UserController;
import com.example.purerestapi.entity.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

  @Override
  public EntityModel<User> toModel(User user) {
    return EntityModel.of(
        user,
        linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
        linkTo(methodOn(UserController.class).getAll()).withRel("allUsers"),
        linkTo(methodOn(UserController.class).getUserNotes(user.getId())).withRel("notes")
    );
  }
}
