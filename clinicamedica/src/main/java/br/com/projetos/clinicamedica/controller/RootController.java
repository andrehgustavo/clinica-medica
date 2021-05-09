package br.com.projetos.clinicamedica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Control all views
 */
@Controller
public class RootController {
    /**
     * pagina index
     * @return pagina
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage(){
        return new ModelAndView("/webapp/AngularJS/index/index.html");
    }

    /**
     * pagina médicos
     * @return pagina
     */
  /*   @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public ModelAndView doctorsPage(){
        return new ModelAndView("/webapp/AngularJS/index/list-doctors.html");
    } */



}
