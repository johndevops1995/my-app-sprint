package io.bootify.my_app_de_john.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservationDTO {

    private Long id;

    @Size(max = 255)
    private String usuario1;

}
