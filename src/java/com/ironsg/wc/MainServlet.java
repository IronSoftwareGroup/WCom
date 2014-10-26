package com.ironsg.wc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/executor"})
public class MainServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(MainServlet.class.getName());

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            LOG.log(Level.INFO, "WebCommander Invoked from ip: {0}", getIp(request));

            String exe = request.getParameter("exe");
            String p1 = request.getParameter("p1");
            String p2 = request.getParameter("p2");
            String p3 = request.getParameter("p3");
            String p4 = request.getParameter("p4");

            LOG.log(Level.INFO, "Executable  is: {0}", exe);
            LOG.log(Level.INFO, "Parameters p1 is: {0}", p1);
            LOG.log(Level.INFO, "Parameters p2 is: {0}", p2);
            LOG.log(Level.INFO, "Parameters p3 is: {0}", p3);
            LOG.log(Level.INFO, "Parameters p4 is: {0}", p4);

            String commandLine = "";
            StringBuilder sb = new StringBuilder();
            sb.append(exe);
            if (p1 != null) {
                sb.append(" ");
                sb.append(p1);
            }
            if (p2 != null) {
                sb.append(" ");
                sb.append(p2);
            }
            if (p3 != null) {
                sb.append(" ");
                sb.append(p3);
            }
            if (p4 != null) {
                sb.append(" ");
                sb.append(p4);
            }

            LOG.log(Level.INFO, "Ready to run command: {0}", sb.toString());
            try {
                Runtime.getRuntime().exec(sb.toString());
                LOG.log(Level.INFO, "Command invoked. You are my hero!!!!!");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Web Commander invoke result</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Success for command " + sb.toString());
                out.println("</body>");
                out.println("</html>");
            } catch (IOException e) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Web Commander invoke result</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>Error for command: " + sb.toString() + "</p>");
                out.println("<p>Error is: " + e.toString() + "</p>");
                out.println("</body>");
                out.println("</html>");
                LOG.log(Level.INFO, "mmm this is bad: {0}", e);
            }

        } finally {
            out.close();
        }
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
            throws ServletException, IOException {

        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Simple Web Commander to execute remote OS shell";
    }// </editor-fold>

    private String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
