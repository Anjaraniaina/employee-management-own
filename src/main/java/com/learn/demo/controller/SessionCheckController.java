package com.learn.demo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SessionCheckController {

    private HttpSession httpSession;

    protected boolean isSessionExpired() {
        return httpSession.getAttribute("username") == null;
    }

    protected String handleSessionExpired() {
        return "redirect:/login?expired";
    }
}