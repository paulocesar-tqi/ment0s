package com.claro.sccweb.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class SCCAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private String targetURL;
	private String afterLoginTargetURL;
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		
		clearAuthenticationAttributes(request);
		if ((targetURL == null) || (targetURL.equals("")))
			targetURL = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetURL);
		getRedirectStrategy().sendRedirect(request, response, targetURL);
	}
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	 
	public String getTargetURL() {
		return targetURL;
	}
	
	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}
	
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {  
        return afterLoginTargetURL;
    }
	
	public String getAfterLoginTargetURL() {
		return afterLoginTargetURL;
	}
	
	public void setAfterLoginTargetURL(String afterLoginTargetURL) {
		this.afterLoginTargetURL = afterLoginTargetURL;
	}
	
}
