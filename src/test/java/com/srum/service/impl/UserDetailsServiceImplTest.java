package com.srum.service.impl;

import com.srum.entity.ClassAdmin;
import com.srum.repository.UserRepository;
import com.srum.util.Messages;
import com.srum.util.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsernameSuccessfully() {
        String username = "admin";
        ClassAdmin classAdmin = new ClassAdmin();
        classAdmin.setAccount(username);
        classAdmin.setPassword("password");

        when(userRepository.getByAccount(username)).thenReturn(classAdmin);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(userDetails.getUsername(), classAdmin.getAccount());
        assertEquals(userDetails.getPassword(), classAdmin.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(item -> Roles.CLASS_ADMIN.equals(item.getAuthority())));
    }

    @Test
    void loadUserByUsernameNullUser() {
        String username = "admin";

        when(userRepository.getByAccount(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
                    userDetailsService.loadUserByUsername(username);
                },
                Messages.INVALID_ACCOUNT);
    }
}