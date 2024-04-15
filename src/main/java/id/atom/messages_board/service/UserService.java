package id.atom.messages_board.service;

import id.atom.messages_board.domain.Role;
import id.atom.messages_board.domain.User;
import id.atom.messages_board.dto.request.SignUpDTO;
import id.atom.messages_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Just user service.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    public void saveUser(SignUpDTO signUpDTO) {
        if (userRepository.existsByLogin(signUpDTO.getLogin())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setLogin(signUpDTO.getLogin());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
