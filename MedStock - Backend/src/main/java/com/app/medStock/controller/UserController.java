package com.app.medStock.controller;

import com.app.medStock.dto.user.Usuario;
import com.app.medStock.dto.user.UsuarioInsert;
import com.app.medStock.enums.RoleUsers;
import com.app.medStock.model.User;
import com.app.medStock.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gustavo
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity create(@RequestBody UsuarioInsert entity) {
        try {
            User save = new User(entity.getNome(), entity.getEmail(), entity.getSenha(), RoleUsers.USER);
            save = userRepository.save(save);
            Usuario usuario = new Usuario(save);
            return ResponseEntity.created(URI.create("api/user/" + usuario.getId())).body(usuario);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioInsert entity) {
        try {
            User user = userRepository.findById(id).get();
            user.setName(entity.getNome());
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getSenha());
            user.setRole(entity.getRole());
            Usuario usuario = new Usuario(user);
            return ResponseEntity.ok(usuario);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = User.class) Predicate predicate) {
        try {
            List<User> users = (List<User>) userRepository.findAll(predicate);
            List<Usuario> usuarios = new ArrayList<>();
            users.forEach(action -> {
                usuarios.add(new Usuario(action));
            });
            return ResponseEntity.ok(usuarios);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            List<User> users = (List<User>) userRepository.findAll();
            List<Usuario> usuarios = new ArrayList<>();
            users.forEach(action -> {
                usuarios.add(new Usuario(action));
            });
            return ResponseEntity.ok(usuarios);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            Usuario usuario = new Usuario(user);
            return ResponseEntity.ok(usuario);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
        }

    }
}
