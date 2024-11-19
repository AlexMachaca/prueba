package learning.DSWII.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.DSWII.code.entity.TUser2;

public interface User2Repository extends JpaRepository<TUser2, String> {
    Optional<TUser2> findByNameUser(String nameUser);
}
