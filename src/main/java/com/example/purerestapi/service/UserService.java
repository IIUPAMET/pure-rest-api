package com.example.purerestapi.service;

import com.example.purerestapi.exception.UserNotFoundException;
import com.example.purerestapi.entity.Note;
import com.example.purerestapi.entity.User;
import com.example.purerestapi.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  public void addUser(User user) {
    userRepository.addUser(user);
  }

  public User getUser(String id) {
    return userRepository.getUser(id).orElseThrow(UserNotFoundException::new);
  }

  public void deleteUser(String id) {
    userRepository.deleteUser(id);
  }

  public List<User> getAll() {
    return userRepository.getAll();
  }

  public User addNote(String userUuid, Note note) {
    var user = this.getUser(userUuid);

    note.setUserUuid(userUuid);
    user.addNote(note);
    return user;
  }

  public List<Note> getAllNotes(String userUuid) {
    var user = this.getUser(userUuid);
    return user.getNotes() == null ? Collections.emptyList() : user.getNotes();
  }
}
