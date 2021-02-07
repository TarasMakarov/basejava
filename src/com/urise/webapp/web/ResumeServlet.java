package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resumes", storage.getAllSorted());
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        printResumes(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

//    private void printResumes(HttpServletResponse response) throws IOException {
//        List<Resume> list = storage.getAllSorted();
//        PrintWriter writer = response.getWriter();
//
//        writer.write(
//                "<title>border-collapse</title>\n" +
//                        "<style>table{border: 2px double black;\n" +
//                        "          border-collapse: collapse;}\n" +
//                        "          th{border: 1px solid black}\n" +
//                        "          td{border: 1px solid black;}\n" +
//                        "</style>\n" +
//                        "<caption><h1>База резюме</h1></caption>\n" +
//                        "<body>\n" +
//                        "<table>\n" +
//                        "<tr>\n" +
//                        "   <th>ФИО</th>\n" +
//                        "   <th>Email</th>\n" +
//                        "</tr>\n");
//
//        for (Resume r : list) {
//            writer.write("<tr>\n");
//            writer.write("  <td><a href=\"resume?uuid=" + r.getUuid() + "\">" + r.getFullName() + "</td>");
//            writer.write("<td>" + r.getContacts(ContactType.EMAIL) + "</td>\n");
//            writer.write("</tr>\n");
//        }
//
//        writer.write("</table>\n" + "</body>");
//    }
}