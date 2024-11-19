package learning.DSWII.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.DSWII.code.entity.TUser;

public interface UserRepository extends JpaRepository<TUser, String> {
    Optional<TUser> findByEmail(String email);
    Optional<TUser> findByDni(String dni);
    
}
