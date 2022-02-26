package com.example.webapp;

import com.example.webapp.telegram.bomber.Attack;
import com.example.webapp.telegram.bomber.AuthProxy;
import com.example.webapp.telegram.bomber.Callback;
import com.example.webapp.utils.TimerUtils;
import jnr.ffi.annotations.In;
import okhttp3.Credentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
