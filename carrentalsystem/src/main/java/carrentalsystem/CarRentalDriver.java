package carrentalsystem;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class CarRentalDriver {

	public static void main(String[] args) {
//		
//		addData();
//		findByDate(LocalDate.now());
//		findByDate();
//		deleteEngId();
//		deAlocateEngine(1);
//		findAllCar();
//		 findCarById();
//		allocateEngine(104, 3);
//		deleteCar(102) ;
//		addEngine();
//		removeEngine(6);
//		findAllEngine() ;
		allocateCarToCustomer(105,1013);
	}

	public static void addData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Engine e = new Engine();
		e.setCc(3200);
		e.setType("Petrol");

		Customer c1 = new Customer();

		c1.setName("Diksha");
		c1.setRentedCar("MG Hector");
		c1.setDuration("1 year");
		c1.setPrice(60000);
		c1.setRentingDate(LocalDate.of(2025, 4, 20));

		Customer c2 = new Customer();
		c2.setName("Tejas");
		c2.setRentedCar("MG Hector");
		c2.setDuration("3 Months");
		c2.setPrice(15000);
		c2.setRentingDate(LocalDate.of(2025, 4, 20));

		List<Customer> li = new ArrayList();
		li.add(c1);
		li.add(c2);

		Car car = new Car();
		car.setBrand("MG");
		car.setModel("MG Hector");
		car.setRegisterDate(LocalDate.of(2025, 4, 12));
		car.setE(e);
		car.setC(li);

		et.begin();
		em.persist(e);
		em.persist(car);
		em.persist(c1);
		em.persist(c2);
		et.commit();
	}

	public static void deleteCar(int c_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Car c = em.find(Car.class, c_id);
		c.setE(null);
		c.setC(null);
		em.remove(c);
		et.commit();
	}

	public static void findCarById() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Car car = em.find(Car.class, 102);
		System.out.println(car.getModel());
		System.out.println(car.getBrand());
		System.out.println(car.getE());

	}

	public static void findCarByRegisterDate() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		String s = "select c from Car c where c.registerDate=?1";
		et.begin();
		Query q = em.createQuery(s);
		q.setParameter(1, LocalDate.of(2025, 4, 12));
		List<Car> li = q.getResultList();
		li.forEach(al -> System.out.println(al.getId()));
		et.commit();
	}

	public static void deleteEngId() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

//		by using find method
		et.begin();
		Car c = em.find(Car.class, 103);
		c.setE(null);
		em.merge(c);
		et.commit();

	}

	public static void allocateEngine(int c_id, int e_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		String sql = "UPDATE car SET e_id = ? WHERE id = ?";

		et.begin();
		Query q = em.createNativeQuery(sql, Car.class);
		q.setParameter(1, e_id);
		q.setParameter(2, c_id);
		int count = q.executeUpdate();
		System.out.println(count);
		et.commit();
	}

	public static void deAlocateEngine(int c_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Car c = new Car();
		em.find(Car.class, c_id);
		c.setE(null);
		em.merge(c);
		et.commit();
	}

	public static void findAllCar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String jpql = "Select c from Car c";
		Query q = em.createQuery(jpql);
		List<Car> c = q.getResultList();
		c.forEach(al -> System.out.println(al));
		et.commit();
	}

	public static void addEngine() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Engine e = new Engine();
		e.setCc(3200);
		e.setType("Petrol");

		et.begin();
		em.persist(e);
		et.commit();
	}

	public static void removeEngine(int e_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Engine e= em.find(Engine.class, e_id);
		em.remove(e);
		et.commit();
	}

	public static void findAllEngine() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String jpql = "Select e from Engine e";
		List<Engine> li = em.createQuery(jpql, Engine.class).getResultList();
		li.forEach(al -> System.out.println(" " + al.getCc() + " " + al.getType()));
		
	}
	public static void allocateCarToCustomer(int c_id, int custo_id) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();

	    Car car = em.find(Car.class, c_id);
	    Customer customer = em.find(Customer.class, custo_id);

	    customer.setRentedCar(car.getModel());

	    em.merge(customer);

	    et.commit();
	}



}