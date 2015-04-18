package com.jingyuan.zhifeng.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jingyuan.zhifeng.core.utils.BaseLogger;


@ControllerAdvice
public class DefaultExceptionHandler extends BaseLogger
{
	@ExceptionHandler()
	public ModelAndView processRuntimeException(NativeWebRequest request, Exception e)
	{
		e.printStackTrace();
		ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("errors/error");
        logger.error(e.getMessage(), e);
        return mv;
	}
}
