package org.gerdoc.servlet;

import org.gerdoc.model.Login;
import org.gerdoc.service.LoginService;
import org.gerdoc.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.net.PasswordAuthentication;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final LoginService loginService = new LoginServiceImpl( );

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String accion = req.getParameter("accion");

        String nombre = req.getParameter("nombre" );
        String password = req.getParameter("password");

        Login login = Login.builder( )
                .nombre( nombre )
                .password( password )
                .build();
        if( "update".equals(accion) )
        {
            loginService.save( login );
        }
        resp.sendRedirect("Logins.jsp");
    }
}
