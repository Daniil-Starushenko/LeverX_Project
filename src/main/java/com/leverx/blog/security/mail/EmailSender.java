package com.leverx.blog.security.mail;

public interface EmailSender {
    void send(String to, String email);
}
