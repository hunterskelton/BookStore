package hw5;

public class Rental 
{
	private String rentedBook, rentee;
	
	public Rental(String rentee, String rentedBook) 
	{
		this.rentee = rentee;
		this.rentedBook = rentedBook;
	}
	public String getRentee() 
	{
		return this.rentee;
	}
	public String getRentedBook() 
	{
		return this.rentedBook;
	}
}
