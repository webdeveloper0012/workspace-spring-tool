
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class StafAuthSuccessHandler implements AuthenticationSuccessHandler
{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * hap faqen homepage perkatese ne baze te roleve qe ka useri i loguar
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) throws IOException, ServletException
	{
		boolean hasUserRole = false;
		boolean hasAdminRole = false;
		
		try
		{
			// merr te gjithe gupet ne te cilen ben pjese useri i loguar
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			// kerkon ne cilin grup ben pjes useri
			for(GrantedAuthority grantedAuthority : authorities)
			{
				if(grantedAuthority.getAuthority().equals("Numeratori_Users"))
				{
					hasUserRole = true;
					break;
				}
				else if(grantedAuthority.getAuthority().equals("Numeratori_Admins"))
				{
					hasAdminRole = true;
					break;
				}
			}
	
			//  ne baze te roleve hap homepage perkates
			if(hasUserRole)
			{
				redirectStrategy.sendRedirect(arg0, arg1, "/homepageUser");
			}
			else if(hasAdminRole)
			{
				redirectStrategy.sendRedirect(arg0, arg1, "/homepageAdmin");
			}
			else
			{
				redirectStrategy.sendRedirect(arg0, arg1, "/login?error");
			}
		}
		catch(Exception e) 
    	{
    		e.printStackTrace();
    		redirectStrategy.sendRedirect(arg0, arg1, "/login?error");
		}
	}
}