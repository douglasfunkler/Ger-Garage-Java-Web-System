package ie.garage.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ie.garage.booking.model.StatusBooking;
import ie.garage.booking.model.Booking;
import ie.garage.booking.repository.Bookings;
import ie.garage.booking.repository.filter.BookingFilter;

@Service
public class RegisterBookingService {

	@Autowired
	private Bookings bookings;

	public void save(Booking booking) {
		try {
			bookings.save(booking);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Invalid Data!");
		}
	}

	public void delete(Long code) {
		bookings.deleteById(code);
	}

	public String receive(Long code) {
		Booking booking = bookings.getOne(code);
		booking.setStatus(StatusBooking.FINISHED);
		bookings.save(booking);

		return StatusBooking.FINISHED.getDescription();

	}

	public List<Booking> filter(BookingFilter filter) {
		String name = filter.getName() == null ? "%" : filter.getName();
		return bookings.findByNameContaining(name);
	}

}
