package com.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EventRegistrationServlet extends HttpServlet {

    private static List<String> registeredEmails = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");
        String ticketsStr = request.getParameter("tickets");

        if (name.isEmpty() || email.isEmpty() || ageStr.isEmpty() || ticketsStr.isEmpty()) {
            out.println("<h3>All fields are required!</h3>");
            return;
        }

        int age = Integer.parseInt(ageStr);
        int tickets = Integer.parseInt(ticketsStr);

        if (age < 18) {
            out.println("<h3>Age restriction: Must be at least 18 years old to register.</h3>");
            return;
        }

        if (registeredEmails.contains(email)) {
            out.println("<h3>Error: This email has already been registered.</h3>");
            return;
        }

        registeredEmails.add(email);

        int costPerTicket = 500;
        int totalCost = tickets * costPerTicket;

        out.println("<h2>Registration Successful!</h2>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<p>Age: " + age + "</p>");
        out.println("<p>Number of Tickets: " + tickets + "</p>");
        out.println("<p>Total Cost: ₹" + totalCost + "</p>");
    }
}
