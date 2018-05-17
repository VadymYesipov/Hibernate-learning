package com.aimprosoft.yesipov.hibernate.web.filter;

import com.aimprosoft.yesipov.hibernate.repository.dao.DepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.EmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLDepartmentDAO;
import com.aimprosoft.yesipov.hibernate.repository.dao.impl.MySQLEmployeeDAO;
import com.aimprosoft.yesipov.hibernate.repository.entity.DepartmentEntity;
import com.aimprosoft.yesipov.hibernate.web.service.ListService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        List<DepartmentEntity> departments = MySQLDepartmentDAO.getMySQLDepartmentDAO().departmentsList();
        if (departments.size() > 0) {
            log.trace("Departments size = " + departments.size());
            request.getServletContext().setAttribute("departmentList", departments);
        }

        DepartmentDAO departmentDAO = MySQLDepartmentDAO.getMySQLDepartmentDAO();
        EmployeeDAO employeeDAO = MySQLEmployeeDAO.getMySQLEmployeeDAO();

        ListService listService = new ListService(departmentDAO, employeeDAO);
        request.getServletContext().setAttribute("employeeList", listService.getEmployeeList());
        request.getServletContext().setAttribute("departmentList", listService.getDepartmentList());

        log.debug("Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
        log.debug("Filter initialization finished");
    }

}
