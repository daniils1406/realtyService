package com.example.authenticationService.security.utils;

import lombok.Data;

@Data
public class Constants {
    public static final String CIAN_USER_LOG_IN_URL="/user/logIn";
    public static final String AGENT_LOG_IN_URL="/agent/logIn";
    public static final String ORGANISATION_LOG_IN_URL="/organisation/logIn";
    public static final String CIAN_USER_GET_ALL="/user/getAll";
    public static final String AGENT_GET_ALL="/agent/getAll";
    public static final String ORGANISATION_GET_ALL="/organisation/getAll";
    public static final String USER_GET_BY_ID="/user/byId";
    public static final String AGENT_GET_BY_ID="/agent/byId";
    public static final String ORGANISATION_GET_BY_ID="/organisation/byId";

}
