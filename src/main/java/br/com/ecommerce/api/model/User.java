package br.com.ecommerce.api.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ecommerce.api.model.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private UserRole role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String prefixSpringSecurity = "ROLE_";
        if(this.role == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority(prefixSpringSecurity+UserRole.ADMIN.name()), new SimpleGrantedAuthority(prefixSpringSecurity+UserRole.USER.name()));
        }else{
            return List.of( new SimpleGrantedAuthority(prefixSpringSecurity+UserRole.USER.name()));
        }
    }
    @Override
    public String getUsername() {
        return email;
    }
}