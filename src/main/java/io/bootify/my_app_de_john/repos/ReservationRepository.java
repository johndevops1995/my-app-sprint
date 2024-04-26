package io.bootify.my_app_de_john.repos;

import io.bootify.my_app_de_john.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
