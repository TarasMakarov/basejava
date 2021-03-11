<%@ page import="com.urise.webapp.model.SimpleTextSection" %>
<%@ page import="com.urise.webapp.model.BulletListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contactsMap}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sectionMap}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section"
                         type="com.urise.webapp.model.AbstractSection"/>
            <tr>
                <td><h2><a name="type.name">${type.title}</a></h2></td>
            </tr>
            <c:choose>

                <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                    <tr>
                        <td>
                            <%=((SimpleTextSection) section).getText()%>
                        </td>
                    </tr>
                </c:when>

                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <tr>
                        <td colspan="2">
                            <c:forEach var="string" items="<%=((BulletListSection)section).getListText()%>">
                            <li> ${string}
                                </c:forEach>
                        </td>
                    </tr>
                </c:when>

                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection)section).getOrganizationList()%>">
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${empty org.organizationLink.url}">
                                        <h4>${org.organizationLink.name}</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4><a href="${org.organizationLink.url}">${org.organizationLink.name}</a></h4>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="vacantion" items="${org.experience}">
                            <jsp:useBean id="vacantion" type="com.urise.webapp.model.Organization.Experience"/>
                            <%
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
                                String dtfStart = dateTimeFormatter.format(vacantion.getStart());
                                String dtfFinish = dateTimeFormatter.format(vacantion.getFinish());
                            %>
                            <tr>
                                <td><%=dtfStart%> - <%=dtfFinish%> </td>
                                <td><b>${vacantion.position}</b><br>${vacantion.duties}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>

            </c:choose>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>