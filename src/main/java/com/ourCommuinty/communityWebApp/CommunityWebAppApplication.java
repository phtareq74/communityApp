package com.ourCommuinty.communityWebApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityWebAppApplication {
	// final Logger log = LoggerFactory.getLogger(CommunityWebAppApplication.class);

	// public static final String DB_NAME = "community.db";

	// public static final String CONNECTION_STRING =
	// "jdbc:sqlserver:\\Users\\phtar\\Downloads\\myJavaProjects\\communityApp\\"
	// +DB_NAME;
	public static final String CONNECTION_STRING = "jdbc:sqlserver://localhost;"
			+ "databaseName=testDB;user = dbo; integratedsecurity=true;";

	public static final String TABLE_Members = "members";
	public static final String MEMBERS_ID = "id";
	public static final String MEMBERS_NAME = "name";
	public static final String MEMBERS_AGE = "age";
	public static final String MEMBERS_EMAIL = "email";
	public static final String MEMBERS_JOB = "job";

	public static final String TABLE_ACTIVITIES = "activities";
	public static final String ACTIVITY_ID = "id";
	public static final String ACTIVITY_NAME = "name";
	public static final String ACTIVITY_PLACE = "place";
	public static final String ACTIVITY_DATE = "date";

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(CommunityWebAppApplication.class, args);
		Member member = new Member(1, "Tareq", 45, "my@email.com", "Web Developer");
		System.out.println("Member information: " + member.getId() + ", " + member.getName() + ", " + member.getAge()
				+ ", " + member.getEmail() + ", " + member.getJob());

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver is loaded");
			Connection conn = DriverManager.getConnection(CONNECTION_STRING);
			Statement memberStatement = conn.createStatement();
			Statement activityStatement = conn.createStatement();

			memberStatement.execute("DROP TABLE IF EXISTS " + TABLE_Members);
			memberStatement.execute(
					"CREATE TABLE " + TABLE_Members +
					" (" + MEMBERS_ID + " INTEGER, "  
			             + MEMBERS_NAME + " TEXT, "
					     + MEMBERS_AGE + " INTEGER, " 
			             + MEMBERS_EMAIL + " TEXT, " 
					     + MEMBERS_JOB + " TEXT " +
			           " )"
			           );
			
 
			insertMembers (memberStatement,1,"Tareq Hammid",45,"my@gemail.com","Developer");
			insertMembers (memberStatement,2,"John John",34,"His@gemail.com","Doctor");
			insertMembers (memberStatement,3,"Jane Jane",29,"her@gemail.com","Lawyer");
			
			ResultSet result = memberStatement.executeQuery("SELECT * FROM members");

			  while (result.next()) {
				System.out.println("Members information: " + result.getString("id") + ", " + result.getString("name")
						+ ", " + result.getString("age") + ", " + result.getString("email") + ", "
						+ result.getString("job")

				);
			}
			  result.close();
				memberStatement.close();
			  
			   activityStatement.execute("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
			   activityStatement.execute("CREATE TABLE " + TABLE_ACTIVITIES + 
						             " (" + ACTIVITY_ID + " INTEGER, "
						                  + ACTIVITY_NAME + " TEXT, " 
						                  + ACTIVITY_PLACE + " TEXT, " 
						                  + ACTIVITY_DATE + " TEXT, " + " )"
						                 );
			   
				
				
			insertActivities(activityStatement, 1, "BBQ", "Amsterdam", "10/AUG/2019");
			insertActivities(activityStatement, 2, "Cultural show", "Amsterdam", "21/Sep/2019");
			
			activityStatement.execute("UPDATE " + TABLE_ACTIVITIES +
					                  " SET " + ACTIVITY_PLACE + "='Utrecht'" 
					                 // + " WHERE CONVERT(VARCHAR," + ACTIVITY_NAME + ") ='BBQ'"
					                  + " WHERE " + ACTIVITY_NAME + " LIKE 'BBQ'"
					                 );
			
			

			ResultSet results = activityStatement.executeQuery("SELECT * FROM activities");

			while (results.next()) {
				System.out.println("Activities information: " + results.getString("id") + ", " + results.getString("name")
						+ ", " + results.getString("place") + ", " + results.getString("date")

				);
			}

			
			results.close();
			activityStatement.close();
			conn.close();
		}

		catch (SQLException e) {
			System.out.println("Something went wrong: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	public static void insertMembers(Statement memberStatement, int id, String name, int age, String email, String job) throws SQLException{
		memberStatement.execute("INSERT INTO " + TABLE_Members +
				" (" + MEMBERS_ID + ", "
				     + MEMBERS_NAME + ", "
				     + MEMBERS_AGE + ", "
				     + MEMBERS_EMAIL + ", " 
				     + MEMBERS_JOB + " )"
				   + "VALUES(" + id + " , '" + name + "'," + age + ",'" + email + "','" + job +"')"
				     );
		
	}
	public static void insertActivities(Statement activityStatement, int id, String name, String place, String date) throws SQLException{
		activityStatement.execute("INSERT INTO " + TABLE_ACTIVITIES + 
				" (" + ACTIVITY_ID + ", " 
				     + ACTIVITY_NAME+ ", "
				     + ACTIVITY_PLACE + ", "
				     + ACTIVITY_DATE + " )"
				   + "VALUES(" + id + ", '" + name + "','" + place + "', '" + date + "')"
				     );	
	}

}
