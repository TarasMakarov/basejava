package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        printResumes(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    private void printResumes(HttpServletResponse response) throws IOException {
        List<Resume> list = storage.getAllSorted();
        PrintWriter writer = response.getWriter();

        writer.write(
                "<title>border-collapse</title>" +
                        "<style>table{border: 2px double black;" +
                        "border-collapse: collapse;}" +
                        "th{border: 1px solid black}" +
                        "td{border: 1px solid black;}</style>" +
                        "<caption><h1>База резюме</h1></caption>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<th>Уникальный идентификатор</th>" +
                        "<th>ФИО</th>" +
                        "</tr>");

        for (Resume r : list) {
            writer.write("<tr>");
            writer.write("<td>" + r.getUuid() + "</td>");
            writer.write("<td>" + r.getFullName() + "</td>");
            writer.write("</tr>");
        }

        writer.write("</table>" + "</body");
    }
}