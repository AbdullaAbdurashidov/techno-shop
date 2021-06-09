package com.codeforce.shop.helper;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieHelper {

    public String getHashID(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
            return null;
        for(Cookie c:cookies){
            if(c.getName().equals("UserID")){
                return c.getValue();
            }
        }
        return null;
    }

    public Cookie setCookie(String name, String value, Integer age, String path){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(age);
        cookie.setPath(path);
        return cookie;
    }

}
