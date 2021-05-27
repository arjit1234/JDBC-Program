import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest 
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		Statement st=null;
		Connection con=null;
		
		try
		{
			sc=new Scanner(System.in);
			String city=null;
			
			if(sc!=null)
			{
				System.out.println("Enter the city name:");
				city=sc.next();
			}
			city="'"+city+"'";
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
			if(con!=null)
				st=con.createStatement();
			
			String query="DELETE FROM STUDENT WHERE SADD="+city;
			
			System.out.println(query);
			
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No Record found");
			else
				System.out.println(count+"Row Deleted");
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Wrong column name or row name or sql query");
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(st!=null)
				{
					st.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if(sc!=null)
				{
					sc.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
