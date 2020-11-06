/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Deeeboo
 */
public class StudentDB {
    private DataSource datasource;

    public StudentDB(DataSource mydatasource) {
        this.datasource = mydatasource;
    }
    
    public List<Student> getStudents() throws Exception{
    
     List<Student> mystudent = new ArrayList<>();
       Connection con = null;
       Statement st = null;
       ResultSet res = null;
       
       try{
           //get connection
       con = datasource.getConnection();
       //create sql statement
       String sql = "select * from student order by last_name";
       st = con.createStatement();
       //execute query
       res  = st.executeQuery(sql);
       //process the result
       while(res.next()){
           //retrieve result from result ser
       int id = res.getInt("id");
       String firstname = res.getString("first_name");
       String lastname = res.getString("last_name");
       String email = res.getString("email");
          
       //create student object
       Student mystud = new Student(id, firstname, lastname, email);
       //add result to list
       mystudent.add(mystud);
       }
       return mystudent;
       }
    finally{
   //close jdbc
   close(con,st,res);
    }
       
    }

    private void close(Connection con, Statement st, ResultSet res) throws SQLException {
        try{
       if(con != null){
          con.close();
       }
       if(st != null){
           st.close();
       }
       if(res != null){
           res.close();
       }
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    }

    void addStudent(Student thestudent) throws SQLException {
        
        Connection con = null;
        PreparedStatement st  = null;
        
        try{
         //get db connection
         con = datasource.getConnection();
         
        //create sql statement for insert
        String sql = "insert into student"
                +"(first_name,last_name,email)"
                +"values (?,?,?)";
        st = con.prepareStatement(sql);
        
        //set the param values for student
        st.setString(1, thestudent.getFirstname());
        st.setString(2, thestudent.getLastname());
        st.setString(3, thestudent.getEmail());
        
        //execute sql insert
        st.execute();
    }
        finally{
          //clean up jdbc
           close(con, st, null);
        }
    }

    Student getStudent(String thestudid) throws Exception {
        Student thestudent = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet res  = null;
        int studentid;
        try{
        //convert studentid to int
        studentid = Integer.parseInt(thestudid);
        //get conn to database
        con = datasource.getConnection();
        //create sql statement
        String sql = "select * from student where id=?";
        //create prepared statement
        st = con.prepareStatement(sql);
        //set params
        st.setInt(1, studentid);
        //execute statement
        res = st.executeQuery();
        //retrieve data from resultset
          if(res.next()){
       String firstname = res.getString("first_name");
       String lastname = res.getString("last_name");
       String email = res.getString("email");
          
       //create student object
       thestudent = new Student(studentid, firstname, lastname, email);
       }
          else{
          throw new Exception("Couldn’t find student id: " + studentid);
          }
       return thestudent;
        }
        finally{
        //close db connection
        close(con, st, res);
        }
    }

   void updateStudent(Student theStudent) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try{
        //get db connection
        con = datasource.getConnection();
        
        //create sql update statement
        
        String sql = "update student "
                +"set first_name=?, last_name=?, email=? "
                +"where id=?";
        //prepare statement
        
        st = con.prepareStatement(sql);
        
        //set param
        st.setString(1, theStudent.getFirstname());
        st.setString(2, theStudent.getLastname());
        st.setString(3, theStudent.getEmail());
        st.setInt(4, theStudent.getId());
        
        //execute sql statement
        st.execute();
    }
            finally {
           close(con, st, null);
}
    }

    	public void deleteStudent(String theStudentId) throws Exception {

		Connection con = null;
		PreparedStatement St = null;
		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			con = datasource.getConnection();
			
			// create sql to delete student
			String sql = "delete from student where id=?";
			
			// prepare statement
			St = con.prepareStatement(sql);
			
			// set params
			St.setInt(1, studentId);
			
			// execute sql statement
			St.execute();
		}
		finally {
			// clean up JDBC code
			close(con, St, null);
		}	
	}
}
