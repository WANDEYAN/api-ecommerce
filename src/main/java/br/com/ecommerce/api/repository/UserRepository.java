package br.com.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
