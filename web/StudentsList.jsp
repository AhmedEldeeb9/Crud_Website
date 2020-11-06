<%-- 
    Document   : StudentsList
    Created on : Oct 10, 2020, 12:32:32 PM
    Author     : Deeeboo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students List</title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>Menoufia University</h2>
            </div>
        </div>
        <div id="container">
            <div id="content">
                <!-- add new student button -->
                <input type="button" value="Add Student"
                       onclick="window.location.href='AddStudentForm.jsp'; return false;"
                      class="add-student-button" />
                <table>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Action</th>
                        
                        <c:forEach var="tempstud" items="${Students_list}">
                            <!-- set a linj for each student -->
                            <c:url var="studlink" value="ServletController">
                                <c:param name="command" value="LOAD" />
                                <c:param name="studentid" value="${tempstud.id}" />
                            </c:url>
                            
                            <!-- set a linj for delete student -->
                            <c:url var="deletelink" value="ServletController">
                                <c:param name="command" value="DELETE" />
                                <c:param name="studentid" value="${tempstud.id}" />
                            </c:url>                           
                    <tr>
                        <td> ${tempstud.firstname} </td>
                        <td> ${tempstud.lastname} </td>
                        <td> ${tempstud.email} </td>
                        <td>
                            <a href="${studlink}">Update</a>
                            |
                            <a href="${deletelink}"
                             onclick="if(!(confirm('Are you sure you want to delete this student ?')))return false"   
                            >Delete</a>
                        </td>
              
                    </tr>
                     </c:forEach>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
