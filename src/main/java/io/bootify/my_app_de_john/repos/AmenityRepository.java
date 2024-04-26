package io.bootify.my_app_de_john.repos;

import io.bootify.my_app_de_john.domain.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
