package ie.garage.booking.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.garage.booking.model.StatusBooking;
import ie.garage.booking.model.VehicleEngineType;
import ie.garage.booking.model.VehicleMake;
import ie.garage.booking.model.VehicleModel;
import ie.garage.booking.model.Booking;
import ie.garage.booking.model.BookingType;
import ie.garage.booking.repository.Bookings;
import ie.garage.booking.repository.filter.BookingFilter;
import ie.garage.booking.service.RegisterBookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	private static final String REGISTER_VIEW = "RegisterBooking";

	@Autowired
	private Bookings bookings;

	@Autowired
	private RegisterBookingService registerBookingService;

	@RequestMapping("/new")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject(new Booking());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Booking booking, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return REGISTER_VIEW;
		}

		try {
			registerBookingService.save(booking);
			attributes.addFlashAttribute("message", "Booked with success!");
			return "redirect:/bookings/new";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("date", null, e.getMessage());
			return REGISTER_VIEW;
		}
	}

	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") BookingFilter filter) {
		List<Booking> allBookings = registerBookingService.filter(filter);

		ModelAndView mv = new ModelAndView("SearchBookings");
		mv.addObject("bookings", allBookings);
		return mv;
	}

	@RequestMapping("{code}")
	public ModelAndView edit(@PathVariable("code") Long codeBooking) {
		Booking booking = bookings.getOne(codeBooking);
		ModelAndView mv = new ModelAndView(REGISTER_VIEW);
		mv.addObject(booking);
		return mv;
	}

	@RequestMapping(value = "{code}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long code, RedirectAttributes attributes) {
		bookings.deleteById(code);

		attributes.addFlashAttribute("message", "Booking successfully deleted!");
		return "redirect:/bookings";
	}

	@RequestMapping(value = "/{code}/receive", method = { RequestMethod.GET, RequestMethod.PUT })
	public @ResponseBody String receive(@PathVariable Long code) {

		return registerBookingService.receive(code);
	}

	@ModelAttribute("allStatusBooking")
	public List<StatusBooking> allStatusBooking() {
		return Arrays.asList(StatusBooking.values());
	}

	@ModelAttribute("allVehicleMake")
	public List<VehicleMake> allVehicleMake() {
		return Arrays.asList(VehicleMake.values());
	}

	@ModelAttribute("allVehicleModel")
	public List<VehicleModel> allVehicleModel() {
		return Arrays.asList(VehicleModel.values());
	}

	@ModelAttribute("allVehicleEngineType")
	public List<VehicleEngineType> allVehicleEngineType() {
		return Arrays.asList(VehicleEngineType.values());
	}

	@ModelAttribute("allBookingType")
	public List<BookingType> allBookingType() {
		return Arrays.asList(BookingType.values());
	}

}
