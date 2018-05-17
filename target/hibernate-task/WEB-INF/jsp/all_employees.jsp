<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>
        <td class="content top">
            <table border='1' bordercolor="red">
                <tr>
                    <th> <fmt:message key="index.jsp.employeeTable.id"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.firstName"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.lastName"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.birthday"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.email"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.position"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.department"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.salary"/> </th>
                </tr>
                <c:forEach var="employee" items="${employeeList}">
                    <tr>
                        <td>
                            <c:out value="${employee.getId()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getFirstName()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getLastName()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getBirthday()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getEmail()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getJob()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getDepartmentByDepartmentId().getOriginalName()}"/>
                        </td>
                        <td>
                            <c:out value="${employee.getSalary()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>

</table>

</body>

</html>
