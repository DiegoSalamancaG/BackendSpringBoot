package proyectoEcommerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.dto.UserResponseDTO;
import proyectoEcommerce.mapper.UserMapper;
import proyectoEcommerce.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    //Test
    @Test
    void postUserTest(){
        //arrange
        UserDTO dto = new UserDTO();
        dto.setName("Diego");
        dto.setUsername("Diego123");
        dto.setPassword("1234");
        dto.setEmail("diego@test1.com");
        dto.setAdmin(false);
        dto.setActive(true);

        User user = new User("Diego","Diego123","1234","diego@test1.com",false,true);
        User savedUser = new User("Diego","Diego123","hashedpass","diego@test1.com",false,true);

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Diego");
        responseDTO.setUsername("Diego123");
        responseDTO.setEmail("diego@test1.com");
        responseDTO.setActive(false);
        responseDTO.setActive(true);

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(dto.getUsername())).thenReturn(Optional.empty());
        when(userMapper.toEntity(dto)).thenReturn(user);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedpass");
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toUserResponseDTO(savedUser)).thenReturn(responseDTO);

        //act
        Optional<UserResponseDTO> result = userService.postUser(dto);

        //assert
        assertTrue(result.isPresent());
        assertEquals("Diego",result.get().getName());
        verify(userRepository).save(user);

    }
}
