package com.example.testejb;

import java.io.*;

import container.Etudinat;
import container.ProcessEtudiantEJBRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @EJB(mappedName="java:global/demo-1.0-SNAPSHOT/ProcessEtudiantEJBImpl!container.ProcessEtudiantEJBImplLocal")
    ProcessEtudiantEJBRemote processEtudiantEJBRemote;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Etudinat obj = new Etudinat();
        obj.setNom("zenagui");
        obj.setPrenom("anas");
        obj.setCne("test");
        obj.setNiveau("sup");
        obj.setAdresse("test");
        processEtudiantEJBRemote.addEtudiant(obj);
        //Etudinat a = processEtudiantEJBRemote.getById(1L);

        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");


    }

    public void destroy() {
    }
}