package com.example.StudentsAPI.Custom;

public class Constants {
    public static final String[] ALLOWED_ENDPOINTS = {
            "/api/v*/register",
            "/api/v*/student/all",
            "/api/v*/student/*",
            "/api/v*/student/find/*/*",
            "/api/v*/subject/all",
            "/api/v*/subject/*"
    };

    public static final String[] AUTH_REQUIRED_ENDPOINTS = {
            "/api/v*/student/create",
            "/api/v*/student/update/*",
            "/api/v*/subject/create/student/*",
            "/api/v*/student/delete/*",
            "/api/v*/subject/delete/*",
            "/api/v*/student/register/student/*/to/subject/*",
    };

    public static final String NOT_FOUND_MSG = "%s with id %s not found!";
    public static final String ALREADY_EXIST = "You've already enrolled to %s subject!";
}
