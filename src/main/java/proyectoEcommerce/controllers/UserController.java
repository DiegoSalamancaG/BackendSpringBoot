package proyectoEcommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.dto.UserResponseDTO;
import proyectoEcommerce.service.UserService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST encargado de manejar operaciones CRUD para usuarios.
 * Todas las rutas requieren que el usuario tenga el rol ADMIN.
 */
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')") // Solo administradores pueden acceder a estos endpoints
public class UserController {

    private final UserService userService;

    /**
     * Constructor que recibe el servicio de usuario.
     *
     * @param userService servicio que contiene la lógica de negocio de usuarios
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtener todos los usuarios registrados.
     *
     * @return lista de usuarios en formato DTO
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Obtener un usuario específico por su ID.
     *
     * @param id identificador del usuario
     * @return usuario encontrado o vacío si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Crear un nuevo usuario.
     *
     * @param userDto objeto DTO con datos del nuevo usuario
     * @return usuario creado (opcional, por si algo falla en el proceso de creación)
     */
    @PostMapping
    public ResponseEntity<Optional<UserResponseDTO>> postUser(@Valid @RequestBody UserDTO userDto) {
        return ResponseEntity.status(201).body(userService.postUser(userDto));
    }

    /**
     * Actualizar un usuario existente.
     *
     * @param id identificador del usuario a modificar
     * @param userDto datos actualizados del usuario
     * @return usuario actualizado si fue encontrado, o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> putUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDto) {
        return userService.updateUser(id, userDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Eliminar un usuario por ID.
     *
     * @param id identificador del usuario
     * @return 204 No Content si fue eliminado, 404 si no fue encontrado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
