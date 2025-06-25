package proyectoEcommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.dto.UserResponseDTO;
import proyectoEcommerce.mapper.UserMapper;
import proyectoEcommerce.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    private User convertDTOToUSer(UserDTO dto){
        User user =  new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setAdmin(dto.getAdmin() != null ? dto.getAdmin():false);
        //si no es null añade el campo o true
        user.setActive(dto.getActive() != null ? dto.getActive():true);

        //no hasheamos contraseña aun
        return user;
    }

    private UserResponseDTO convertUserToUserDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAdmin(user.getAdmin());
        dto.setActive(user.getActive());

        return dto;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findByActiveTrue()
                .stream()
                .map(this::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertUserToUserDTO);
    }

    @Override
    public Optional<UserResponseDTO> postUser(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email ya registrado.");
        }
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username ya en uso.");
        }

        //traemos los datos del usuarioDTO al nuevo usuario
        User user = convertDTOToUSer(dto);

        //ahora si hasheamos la contraseña
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return Optional.of(convertUserToUserDTO(userRepository.save(user)));
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserDTO dto) {
        return userRepository.findById(id).map(user -> {
            user.setName(dto.getName());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setAdmin(dto.getAdmin());
            user.setActive(dto.getActive());

            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            User userUpdated = userRepository.save(user);
            return convertUserToUserDTO(userUpdated);
        });
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
