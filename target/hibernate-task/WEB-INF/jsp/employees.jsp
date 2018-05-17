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
        <td class="content top">

            <form class="login_form" method="post" action="controller">
                <input type="hidden" required name="command" value="addEdit"/></p>
                <input type="hidden" required name="name" value="employees"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.addEditEmployee"/>">
            </form>

            <form class="login_form" method="post" action="controller">
                <input type="hidden" required name="command" value="list"/></p>
                <input type="hidden" required name="name" value="departments"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.departments"/>"/>
            </form>

            <form class="login_form" action="controller" method="post">
                <fieldset>
                    <input type="hidden" name="command" value="removeEmployee"/></p>
                    <select name="email" required>
                        <c:forEach var="employee" items="${employeeList}">
                            <option value="<c:out value="${employee.getEmail()}"/>"> <c:out value="${employee.getEmail()}" /> </option>
                        </c:forEach>
                    </select>
                    <input type="submit" name="removeEmployee" value="<fmt:message key="index.jsp.submit.remove"/>"/>
                </fieldset>
            </form>
        </td>
    </tr>

</table>

</body>
</html>