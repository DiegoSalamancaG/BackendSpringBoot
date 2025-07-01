package proyectoEcommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuarios", description = "Operaciones administrativas sobre los usuarios")
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
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve una lista de todos los usuarios registrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de usuarios obtenida exitosamente",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponseDTO.class))
    )
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
    @Operation(
            summary = "Obtener usuarios por ID",
            description = "Devuelve un usuario específico según su ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )}
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo usuario.
     *
     * @param userDto objeto DTO con datos del nuevo usuario
     * @return usuario creado (opcional, por si algo falla en el proceso de creación)
     */
    @Operation(
            summary = "Crear un nuevo Usuario",
            description = "Registra un nuevo usuario en el sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",description = "Usuario creado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "400", description = "Datos inválidos en la solicitud"
            )
    })
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
    @Operation(
            summary = "Actualiza un usuario",
            description = "Modifica un usuario existente según su ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",description = "Usuario modificado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404", description = "Usuario no encontrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> putUser(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id, @Valid @RequestBody UserDTO userDto) {
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
    @Operation(
            summary = "Elimina un usuario",
            description = "Elimina un usuario existente según su ID"
    )@ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuario eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        return userService.deleteUser(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
