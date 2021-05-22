package com.pxl.common.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static HttpServletRequest getRequest(){
        return local.get();
    }
    public static void setRequest(HttpServletRequest httpServletRequest){
        local.set(httpServletRequest);
    }

    public static String getBrowser(HttpServletRequest request){
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }
}
