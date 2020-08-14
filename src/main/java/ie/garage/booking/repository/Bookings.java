package ie.garage.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.garage.booking.model.Booking;

public interface Bookings extends JpaRepository<Booking, Long> {

	public List<Booking> findByNameContaining(String name);

}
