package dev.gsitgithub.security.preauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

public class CustomPreAuthenticatedProcessingFilter extends RequestHeaderAuthenticationFilter {

	private String principalRequestHeader = "LAB_SOLUTIONS";
	
	/*@Autowired
	CustomUserDetailsService customUserDetailService;*/
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
    	response.addHeader(principalRequestHeader, "myvalues");
    	System.out.println("\nsuccessfulAuthentication\n");
    	super.successfulAuthentication(request, response, authResult);
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
    		HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		System.out.println("\n unsuccessfulAuthentication \n");
		super.unsuccessfulAuthentication(request, response, failed);
    }
    
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		System.out.println("\n getPreAuthenticatedPrincipal "+request.getServletPath()+"\n");
		if (request.getParameterMap().size() >= 1)
	        return true;
	    return false;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		System.out.println("\ncredentialsRequestHeader\n");
		String[] credentials = new String[2];
	    credentials[0] = request.getParameter("username");
	    credentials[1] = request.getParameter("password");
	    return credentials;
	}

}
