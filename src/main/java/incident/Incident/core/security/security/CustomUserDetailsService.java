package incident.Incident.core.security.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import incident.Incident.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        incident.Incident.domain.User user = this.repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

        UserDetails userDetails = User.builder().username(user.getEmail()).password(user.getPassword()).roles("ADMIN").build();

        return userDetails;

    }
    
}
