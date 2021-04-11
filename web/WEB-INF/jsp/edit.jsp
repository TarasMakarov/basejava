<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.BulletListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.util.YearMonthUtil" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" required="required" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section"
                         type="com.urise.webapp.model.AbstractSection"/>
            <h3><a>${type.title}</a></h3>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <dd>
                        <input type="text" name="${type}" size="104" value="<%=section%>">
                    </dd>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                        <dd>
                            <textarea name="${type}" cols=105
                                   rows=5><%=String.join("\n",((BulletListSection)section).getListText())%> </textarea>
                        </dd>
                </c:when>
                <c:when test="${type=='EXPERIENCE'}">
                    <c:forEach var="org" items="<%=((OrganizationSection)section).getOrganizationList()%>"
                               varStatus="on">
                        <dl>
                            <dt>Название организации</dt>
                            <dd>
                                <input type="text" name="${type}" size="83" value="${org.organizationLink.name}">
                            </dd>
                        </dl>
                        <dl>
                            <dt>Сайт организации</dt>
                            <dd>
                                <input type="text" name="${type}url" size="83" value="${org.organizationLink.url}">
                            </dd>
                        </dl>
                        <c:forEach var="exp" items="${org.experience}">
                            <jsp:useBean id="exp" type="com.urise.webapp.model.Organization.Experience"/>
                            <dl>
                                <dt>Начало сотрудничества:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}start" size="15"
                                           value="<%=YearMonthUtil.formatDate(exp.getStart())%>" placeholder="MM/yyyy">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Окончание сотрудничества:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}finish" size="15"
                                           value="<%=YearMonthUtil.formatDate(exp.getFinish())%>" placeholder="MM/yyyy">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Должность:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}position" size="83"
                                           value=${exp.position}>
                                </dd>
                            </dl>
                            <dl>
                                <dt>Обязанности:</dt>
                                <dd>
                                    <textarea name="${type}${on.index}duties" rows=5
                                              cols="84(">${exp.duties}</textarea>
                                </dd>
                            </dl>
                        </c:forEach>
                    </c:forEach>
                </c:when>
                <c:when test="${type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection)section).getOrganizationList()%>"
                               varStatus="on">
                        <dl>
                            <dt>Название учебного заведения</dt>
                            <dd>
                                <input type="text" name="${type}" size="83" value="${org.organizationLink.name}">
                            </dd>
                        </dl>
                        <dl>
                            <dt>Сайт учебного заведения</dt>
                            <dd>
                                <input type="text" name="${type}url" size="83" value="${org.organizationLink.url}">
                            </dd>
                        </dl>
                        <c:forEach var="edu" items="${org.experience}">
                            <jsp:useBean id="edu" type="com.urise.webapp.model.Organization.Experience"/>
                            <dl>
                                <dt>Начало обучения:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}start" size="15"
                                           value="<%=YearMonthUtil.formatDate(edu.getStart())%>" placeholder="MM/yyyy">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Окончание обучения:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}finish" size="15"
                                           value="<%=YearMonthUtil.formatDate(edu.getFinish())%>" placeholder="MM/yyyy">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Специальность:</dt>
                                <dd>
                                    <input type="text" name="${type}${on.index}position" size="83"
                                           value=${edu.position}>
                                </dd>
                            </dl>
                            <dl>
                                <dt>Квалификация:</dt>
                                <dd>
                                    <textarea name="${type}${on.index}duties" rows=5
                                              cols="84(">${edu.duties}</textarea>
                                </dd>
                            </dl>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>