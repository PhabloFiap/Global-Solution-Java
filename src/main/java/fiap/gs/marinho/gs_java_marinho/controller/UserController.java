package fiap.gs.marinho.gs_java_marinho.controller;

import fiap.gs.marinho.gs_java_marinho.entity.User;
import fiap.gs.marinho.gs_java_marinho.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuario")
//@Api(tags = "User")
public class UserController {



    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
//    @ApiOperation(value = "Listar usuários")
    public List<EntityModel<User>> listUser(){
        List<User> users = userService.listUser();
        return users.stream()
                .map(user -> EntityModel.of(user,
                        WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users")))
                .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{usuario}")
//    @ApiOperation(value = "Buscar usuário por nome de usuário")
    public ResponseEntity<EntityModel<User>> getUserByUsuario(@PathVariable String usuario) {
        Optional<User> userOptional = userService.getUserByUsuario(usuario);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            EntityModel<User> resource = EntityModel.of(user);
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserByUsuario(usuario)).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
//    @ApiOperation(value = "Buscar usuário por ID")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            EntityModel<User> resource = EntityModel.of(user);
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
//    @ApiOperation(value = "Registrar usuário")
    public ResponseEntity<EntityModel<User>> registerUser(@RequestBody User user) {
        User registeredUser = userService.createUser(user);
        EntityModel<User> resource = EntityModel.of(registeredUser);
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserById(registeredUser.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users"));
        return ResponseEntity.ok(resource);
    }

    @PutMapping
//    @ApiOperation(value = "Atualizar usuário")
    public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        EntityModel<User> resource = EntityModel.of(updatedUser);
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserById(updatedUser.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users"));
        return ResponseEntity.ok(resource);
    }
    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Deletar usuário")
    public ResponseEntity<List<EntityModel<User>>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        List<User> users = userService.listUser();
        List<EntityModel<User>> resources = users.stream()
                .map(user -> EntityModel.of(user,
                        WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(UserController.class).listUser()).withRel("users")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }




}