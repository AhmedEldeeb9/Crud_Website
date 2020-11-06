<%-- 
    Document   : Add StudentForm
    Created on : Oct 27, 2020, 4:34:50 AM
    Author     : Deeeboo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Student</title>
        <link type="text/css" rel="stylesheet" href="css/style.css" >
        <link type="text/css" rel="stylesheet" href="css/add-student-style.css" >
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>Menoufia University</h2>
            </div>
        </div>
        <div id="container">
            <h3> Update Student </h3>
            <form action="ServletController" method="GET">
              <input type="hidden" name="command" value="UPDATE" />
              <input type="hidden" name="studentid" value="${the_student.id}" />
                <table>
                    <tbody>
                        <tr>
                            <td><label>First Name:</label></td>
                            <td><input type="text" name="FirstName" 
                                       value="${the_student.firstname}"/></td>
                        </tr>
                        <tr>
                            <td><label>Last Name:</label></td>
                            <td><input type="text" name="LastName"
                                       value="${the_student.lastname}"/></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><input type="text" name="Email"
                                       value="${the_student.email}"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" class="save" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <div style="clear: both;"></div>
            <p>
                <a href="ServletController">Back to List </a>
            </p>
        </div>
    </body>
</html>
