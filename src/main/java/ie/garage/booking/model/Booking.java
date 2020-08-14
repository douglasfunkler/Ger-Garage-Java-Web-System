package ie.garage.booking.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@NotBlank(message = "Name mandatory!")
	@Size(max = 60, message = "Name cannot contain more than 60 characters")
	private String name;

	@NotBlank(message = "Phone mandatory!")
	@Size(max = 20, message = "Phone cannot contain more than 60 characters")
	private String phone;

	@NotNull(message = "Date is mandatory!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Size(max = 500, message = "Comments cannot contain more than 500 characters")
	private String comments;

	@NotNull(message = "Price is mandatory!")
	@DecimalMin(value = "0.01", message = "Price cannot be inferior to 0,01")
	@DecimalMax(value = "9999999.99", message = "Price cannot be higher than 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private VehicleMake make;

	@Enumerated(EnumType.STRING)
	private VehicleEngineType engine;

	@Enumerated(EnumType.STRING)
	private VehicleModel model;

	@Enumerated(EnumType.STRING)
	private BookingType bookingType;

	@Enumerated(EnumType.STRING)
	private StatusBooking status;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}

	public VehicleMake getMake() {
		return make;
	}

	public void setMake(VehicleMake make) {
		this.make = make;
	}

	public VehicleEngineType getEngine() {
		return engine;
	}

	public void setEngine(VehicleEngineType engine) {
		this.engine = engine;
	}

	public VehicleModel getModel() {
		return model;
	}

	public void setModel(VehicleModel model) {
		this.model = model;
	}

	public BookingType getBookingType() {
		return bookingType;
	}

	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}

	public StatusBooking getStatus() {
		return status;
	}

	public void setStatus(StatusBooking status) {
		this.status = status;
	}

	public boolean isPending() {
		return StatusBooking.PROGRESS.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
