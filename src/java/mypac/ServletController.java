/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypac;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 *
 * @author Deeeboo
 */
public class ServletController extends HttpServlet {

         private StudentDB studentdb;
         @Resource(name="jdbc/web_student_tracker")
         private DataSource datasource;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        try{
          studentdb = new StudentDB(datasource);
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
    }
         
         
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      {
   
             try {
                 
                 //read the command parameter
                 String thecommand = request.getParameter("command");
                 
                 //if the command is missing data then go to liststudent
                    if(thecommand == null){
                    thecommand = "LIST";
                    }
                 
                    //route the appriciate method
                    switch (thecommand){
                        case "LIST":
                            ListStudents(request, response);
                            break;
                            
                        case "ADD":
                                addStudent(request, response);
                                break;
                        
                        case "LOAD":
                            loadStudent(request, response);
                            break;
                        case "UPDATE":
                            updateStudent(request, response);
                            break;
 			case "DELETE":
				deleteStudent(request, response);
				break;                                   
                        default:
                            ListStudents(request, response);
                    }
                
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
  
    }
    	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theStudentId = request.getParameter("studentid");
		
		// delete student from database
		studentdb.deleteStudent(theStudentId);
		
		// send them back to "list students" page
		ListStudents(request, response);
	}
        private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
            //read student info from form data
            int id = Integer.parseInt(request.getParameter("studentid"));
            String firstName = request.getParameter("FirstName");
            String lastName = request.getParameter("LastName");
            String email = request.getParameter("Email");
            
            //create new student object
            Student theStudent = new Student(id, firstName, lastName, email);
            //perform upfate on database
            studentdb.updateStudent(theStudent);
            //send them back to liststudents
             ListStudents(request, response);
    }

        private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception  {
            //read student id
            String thestudid = request.getParameter("studentid");
            //get student from database
            Student thestudent = studentdb.getStudent(thestudid);
            //place student in request attribute
            request.setAttribute("the_student", thestudent);
            //send it to jsp page
            RequestDispatcher dis =  request.getRequestDispatcher("/updatestudent.jsp");
            dis.forward(request, response);
    }
        private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
            
            //read student info from data
            String firstname = request.getParameter("FirstName");
            String lastname = request.getParameter("LastName");
            String email = request.getParameter("Email");

            //create new student obj
            Student thestudent = new Student(firstname, lastname, email);
            
            //add the student to database
            studentdb.addStudent(thestudent);
            
            //send back to main page
            ListStudents(request, response);
    }
        
    private void ListStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    // get students from db
       List<Student> students = studentdb.getStudents();
       //add them to request 
       request.setAttribute("Students_list", students);
       //send it to jsp page
        RequestDispatcher req = request.getRequestDispatcher("/StudentsList.jsp");
        req.forward(request, response);
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>








}
