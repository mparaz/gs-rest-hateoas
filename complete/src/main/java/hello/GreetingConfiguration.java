package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class GreetingConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
                resp.setHeader("X-prehandle", "got: " + req.getHeader("X-prehandle"));
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView m) {
                resp.setHeader("X-posthandle", "true");
            }

            @Override
            public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {

            }
        });
    }
}
