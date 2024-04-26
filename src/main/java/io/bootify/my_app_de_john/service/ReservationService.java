package io.bootify.my_app_de_john.service;

import io.bootify.my_app_de_john.domain.Reservation;
import io.bootify.my_app_de_john.domain.User;
import io.bootify.my_app_de_john.model.ReservationDTO;
import io.bootify.my_app_de_john.repos.ReservationRepository;
import io.bootify.my_app_de_john.repos.UserRepository;
import io.bootify.my_app_de_john.util.NotFoundException;
import io.bootify.my_app_de_john.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(final ReservationRepository reservationRepository,
            final UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public List<ReservationDTO> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("id"));
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .toList();
    }

    public ReservationDTO get(final Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ReservationDTO reservationDTO) {
        final Reservation reservation = new Reservation();
        mapToEntity(reservationDTO, reservation);
        return reservationRepository.save(reservation).getId();
    }

    public void update(final Long id, final ReservationDTO reservationDTO) {
        final Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationDTO, reservation);
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO mapToDTO(final Reservation reservation,
            final ReservationDTO reservationDTO) {
        reservationDTO.setId(reservation.getId());
        reservationDTO.setUsuario1(reservation.getUsuario1());
        return reservationDTO;
    }

    private Reservation mapToEntity(final ReservationDTO reservationDTO,
            final Reservation reservation) {
        reservation.setUsuario1(reservationDTO.getUsuario1());
        return reservation;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final User usuario1User = userRepository.findFirstByUsuario1(reservation);
        if (usuario1User != null) {
            referencedWarning.setKey("reservation.user.usuario1.referenced");
            referencedWarning.addParam(usuario1User.getId());
            return referencedWarning;
        }
        return null;
    }

}
