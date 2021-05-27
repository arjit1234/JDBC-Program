import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest2 
{
	public static void main(String[] args) {
		Scanner sc=null;
		Statement st=null;
		Connection con=null;
		
		try
		{
			sc=new Scanner(System.in);
			int sno=0;
			if(sc!=null)
			{
				System.out.println("Enter Student No:");
				sno=sc.nextInt();
			}
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
			if(con!=null)
				st=con.createStatement();
			
			String query="DELETE FROM STUDENT WHERE SNO="+sno;
			System.out.println(query);
			
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No Records Found");
			else
				System.out.println(count+"  Records are Deleted");
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid row or column or query");
			
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
