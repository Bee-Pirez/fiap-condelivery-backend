package br.com.condelivery.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private boolean isDeliveryMan;
    private String imgUrl;
    private Condominium condominium;
    private String block;
    private String apartment;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        /*if (isDeliveryMan) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_DELIVERYMAN"),
                    new SimpleGrantedAuthority("ROLE_RESIDENT")
            );
        } else {
            // O RESIDENT tem apenas a permissão de RESIDENT
            return List.of(new SimpleGrantedAuthority("ROLE_RESIDENT"));
        }

         */
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
