package ua.goit.java8.javadeveloper.service;


public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
