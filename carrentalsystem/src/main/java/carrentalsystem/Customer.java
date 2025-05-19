package carrentalsystem;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cost_id")
	@SequenceGenerator(name = "cost_id", initialValue = 1000, allocationSize = 1)
	private int id;
	private String name;
	private LocalDate rentingDate;
	private String rentedCar;
	private double price;
	private String duration;

	@OneToOne
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getRentingDate() {
		return rentingDate;
	}

	public void setRentingDate(LocalDate rentingDate) {
		this.rentingDate = rentingDate;
	}

	public String getRentedCar() {
		return rentedCar;
	}

	public void setRentedCar(String rentedCar) {
		this.rentedCar = rentedCar;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
