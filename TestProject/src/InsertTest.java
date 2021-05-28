import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest
{
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try
		{
			sc=new Scanner(System.in);
			int no=0;
			String name=null,add=null;
			float avg=0.0f;
			for(int i=1;i<=3;i++)
			{
				if(sc!=null)
				{
					System.out.println("Enter student no.");
					no=sc.nextInt();
					System.out.println("Enter student name:");
					name=sc.next();
					System.out.println("Enter student Address:");
					add=sc.next();
					System.out.println("Enter the avg:");
					avg=sc.nextFloat();
					
				}
				//convert input value as required for sql query
				name="'"+name+"'";
				add="'"+add+"'";
			
				//establish connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
				//create statement object
				if(con!=null)
					st=con.createStatement();
				String query=null;
			
				//prepare the query
				query="insert into student values("+no+","+name+","+add+","+avg+")";
				System.out.println(query);
			
			
				//send and execute the query
				int count=0;
				if(st!=null)
					count=st.executeUpdate(query);
			
				if(count==0)
					System.out.println("No rows inserted");
				else
					System.out.println(count+" Rows inserted");
			}
		}
		catch(SQLException se) //known Exception
		{
			if(se.getErrorCode()==1)
				System.out.println("Cannot insert duplicate values in pk");
			if(se.getErrorCode()==1400)
				System.out.println("cannot insert NULL");
			
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid column or row or sql statement");
		}
		catch(Exception e)//known exception
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
