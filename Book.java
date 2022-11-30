package hw5;

public class Book 
{
	private String title, authorFN, authorLN, publisher;
	private int pageCount, stock;
	
	public Book(String title, String authorFN, String authorLN, int pageCount, String publisher, int stock) 
	{
		this.title = title;
		this.authorFN = authorFN;
		this.authorLN = authorLN;
		this.pageCount = pageCount;
		this.publisher = publisher;
		this.stock = stock;
	}
	public String getTitle() 
	{
		return this.title;
	}
	public String getAuthorFN() 
	{
		return this.authorFN;
	}
	public String getAuthorLN() 
	{
		return this.authorLN;
	}
	public int getPageCount() 
	{
		return this.pageCount;
	}
	public String getPublisher() 
	{
		return this.publisher;
	}
	public int getStock() 
	{
		return this.stock;
	}
}
