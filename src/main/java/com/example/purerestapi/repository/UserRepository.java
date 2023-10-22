package com.example.purerestapi.repository;

import com.example.purerestapi.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final static Map<String, User> users = new HashMap<>();


  public List<User> getAll() {
    return users.values().stream().toList();
  }
  public void addUser(User user) {
    if (user.getId() == null) {
      user.setId(UUID.randomUUID().toString());
    }
    if (!users.containsKey(user.getId())) {
      users.put(user.getId(), user);
    }
  }

  public Optional<User> getUser(String id) {
    return Optional.ofNullable(users.get(id));
  }

  public void deleteUser(String id) {
    users.remove(id);
  }
}
