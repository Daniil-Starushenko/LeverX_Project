package com.leverx.blog.exception.handlers;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class HandlerExceptionResolverProxy {

    private HandlerExceptionResolver handlerExceptionResolver;


    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Exception exception) {
        return handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, exception);
    }

}