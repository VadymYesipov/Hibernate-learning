<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>

        <td class="content center">
            <form class="login_form" name="myForm" action="controller" method="post" onclick="validateBirthday()">
                <fieldset>
                    <input type="hidden" name="command" value="addEmployee"/></p>
                    <input type="text" value="${add_first_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${add_last_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${add_birth}" required name="birthday"/></p>
                    <input type="text" value="${add_mail}" required name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${add_job}" required pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${add_department_id}" required min="1" max="${departmentList.get(departmentList.size() - 1).getId()}" name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${add_wage}" required min="100" step="10" max="5000" name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>"/>
                </fieldset>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" action="controller" method="post">
                <fieldset>
                    <input type="hidden" name="command" value="editEmployee" /></p>
                    <input type="number" value="${edit_ID}" min="1" max="${employeeList.get(employeeList.size() - 1).getId()}" required name="id" placeholder="<fmt:message key="index.jsp.placeholder.chooseId"/>"/></p>
                    <input type="number" value="${new_edit_ID}" min="1" name="newId" placeholder="<fmt:message key="index.jsp.placeholder.newId"/>"/></p>
                    <input type="text" value="${edit_first_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${edit_last_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${edit_birth}" name="birthday"/></p>
                    <input type="text" value="${edit_mail}" name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${edit_job}" pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${edit_department_id}" min="1" max="${departmentList.get(departmentList.size() - 1).getId()}" name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${edit_wage}" min="100" step="10" max="5000" name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="submit" name="editEmployee" value="<fmt:message key="index.jsp.submit.edit"/>" />
                </fieldset>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" method="post" action="/controller">
                <input type="hidden" required name="command" value="list"/></p>
                <input type="hidden" required name="name" value="employees"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.employees"/>">
            </form>
        </td>

        <form class="login_form" method="post" action="/controller">
            <input type="hidden" required name="command" value="list"/></p>
            <input type="hidden" required name="name" value="departments"/></p>
            <input type="submit" value="<fmt:message key="index.jsp.placeholder.departments"/>"/>
        </form>

    </tr>

</table>

</body>

</html>
