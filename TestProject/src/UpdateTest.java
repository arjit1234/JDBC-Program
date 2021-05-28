import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest 
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		Statement st=null;
		Connection con=null;
		
		try
		{
			sc=new Scanner(System.in);
			String sname=null;
			String sadd=null;
			float avg=0;
			int sno=0;
			if(sc!=null)
			{
				System.out.println("Enter the Student name");
				sname=sc.nextLine();
				System.out.println("Enter Student Address:");
				sadd=sc.nextLine();
				System.out.println("Enter avg:");
				avg=sc.nextFloat();
				System.out.println("Enter Student No:");
				sno=sc.nextInt();
			}
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
			if(con!=null)
				st=con.createStatement();
			String query="update student set sname="+sname+", sadd="+sadd+" ,avg="+avg+" where sno="+sno;
			
			System.out.println(query);
			
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No Record Found");
			else
				System.out.println(count+" Records Deleted");
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid column name or row name or query");
			
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	try
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
