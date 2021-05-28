import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest2 
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try
		{
			sc=new Scanner(System.in);
			String desg1=null,desg2=null,desg3=null;
			float hikepercentage=0.0f;
			if(sc!=null)
			{
				System.out.println("Enter desg1:");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter desg2:");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter desg3:");
				desg3=sc.next().toUpperCase();
				System.out.println("Enter hike percentage");
				hikepercentage=sc.nextFloat();
			}
			//convert input value as required for sql query
			desg1="'"+desg1+"'";
			desg2="'"+desg2+"'";
			desg3="'"+desg3+"'";
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
			if(con!=null)
				st=con.createStatement();
			//prepare sql query
			String query="update emp set sal=sal+(sal*"+hikepercentage/100+") where job in("+desg1+","+desg2+","+desg3+")";
			
			System.out.println(query);
			
			//send and execute sql query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No rows updated");
			else
				System.out.println(count+"Rows updated");
			
			
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid row name or column name or SQL keyword");
			if(se.getErrorCode()==12899)
				System.out.println("Do not insert more than column size date to sname,sadd");
		}
		catch(Exception e)//unknown execption
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
