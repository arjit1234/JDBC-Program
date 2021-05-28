import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest1 
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		Statement st=null;
		Connection con=null;
		
		try
		{
			sc=new Scanner(System.in);
			int eno=0;
			String name=null,job=null;
			float sal=0.0f;
			if(sc!=null)
			{
				System.out.println("Enter Employee no:");
				eno=sc.nextInt();
				System.out.println("Enter name:");
				name=sc.next();
				System.out.println("Enter Desgignation");
				job=sc.next();
				System.out.println("Enter sal:");
				sal=sc.nextFloat();
			}
			//convert input values as required for sql queris
			name="'"+name+"'";
			job="'"+job+"'";
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TIGER","ROOT");
			
			//create statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare sql query
			String query="insert into emp(empno,ename,job,sal) values("+eno+","+name+","+job+","+sal+")";
			System.out.println(query);
			
			//send and execute the query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("No Records are inserted");
			else
				System.out.println(count+" Records are inserted");
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
