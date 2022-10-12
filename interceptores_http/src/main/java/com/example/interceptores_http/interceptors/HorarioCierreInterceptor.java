package com.example.interceptores_http.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horarioCierreInterceptor")
public class HorarioCierreInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;

    private static final Logger log = LoggerFactory.getLogger(HorarioCierreInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("HorarioCierreInterceptor: preHandler -> Entrando...");

        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if (!(hora >= apertura && hora < cierre)) {

            StringBuilder mensaje = new StringBuilder("Cerrado, vuelve en otra ocasión");
            mensaje.append(", atendemos desde las ");
            mensaje.append(apertura);
            mensaje.append(" hrs. hasta las ");
            mensaje.append(cierre);
            mensaje.append(" hrs.");

            request.setAttribute("mensaje", mensaje.toString());

            return true;
        }

        response.sendRedirect(request.getContextPath().concat("/index"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {

        String mensaje = (String) request.getAttribute("mensaje");

        if (modelAndView != null && handler instanceof HandlerMethod) {
            modelAndView.addObject("mensajeCierre", mensaje);
        }

        log.info("HorarioCierreInterceptor: postHandler -> Saliendo...");

    }

}
