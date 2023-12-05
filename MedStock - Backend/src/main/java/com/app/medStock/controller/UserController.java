package com.app.medStock.controller;

import com.app.medStock.RequestRateLimiter;
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

    @Autowired
    private RequestRateLimiter rateLimiter;

    @PostMapping
    public ResponseEntity create(@RequestBody UsuarioInsert entity) {
        if (rateLimiter.tryAcquire()) {
            try {
                User save = new User(entity.getNome(), entity.getEmail(), entity.getSenha(), RoleUsers.USER);
                save = userRepository.save(save);
                Usuario usuario = new Usuario(save);
                return ResponseEntity.created(URI.create("api/user/" + usuario.getId())).body(usuario);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioInsert entity) {
        if (rateLimiter.tryAcquire()) {
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
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/querydsl")
    public ResponseEntity getBatch(@QuerydslPredicate(root = User.class) Predicate predicate) {
        if (rateLimiter.tryAcquire()) {
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
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        if (rateLimiter.tryAcquire()) {
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
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                User user = userRepository.findById(id).orElse(null);
                Usuario usuario = new Usuario(user);
                return ResponseEntity.ok(usuario);
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        if (rateLimiter.tryAcquire()) {
            try {
                userRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception err) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getLocalizedMessage());
            }
        } else {
            return ResponseEntity.status(429).body("Muitas solicitações, limite de requisições foi excedido");
        }
    }
}
