package p0.service;

import p0.pojos.Car;
import p0.pojos.User;

public interface DAOpersistable {
	public boolean registerDao(User user);

	public boolean loginDao(User user);

	public boolean addCarDao(Car car);

	public boolean removeCarDao(Car car);

	public boolean acceptOfferDao(User user, Car car);
	
	public boolean rejectOfferDao(User user, Car car);

	public boolean makeOfferDao(User user, Car car);

	public boolean viewPaymentsDao();

	public boolean viewLotDao();

	public boolean viewOwnedCarsDao(User user);

	public boolean viewRemPaymentsDao(User user);
}
