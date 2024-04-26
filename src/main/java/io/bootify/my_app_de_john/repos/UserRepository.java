package io.bootify.my_app_de_john.repos;

import io.bootify.my_app_de_john.domain.Reservation;
import io.bootify.my_app_de_john.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsuario1(Reservation reservation);

}
