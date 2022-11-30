package hw5;

public class Customer 
{
	private String customerFN, customerLN, emailAddress;
	private String phoneNumber;
	private int rentals;
	
	public Customer(String customerFN, String customerLN, String emailAddress, String phoneNumber, int rentals) 
	{
		this.customerFN = customerFN;
		this.customerLN = customerLN;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.rentals = rentals;
	}
	public String getCustomerFN() 
	{
		return this.customerFN;
	}
	public String getCustomerLN() 
	{
		return this.customerLN;
	}
	public String getEmailAddress() 
	{
		return this.emailAddress;
	}
	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}
	public int getRentals() 
	{
		return this.rentals;
	}
}
