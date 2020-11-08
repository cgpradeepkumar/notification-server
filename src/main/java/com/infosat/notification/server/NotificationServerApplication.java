package com.infosat.notification.server;

import com.infosat.notification.server.component.NotificationScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class NotificationServerApplication implements CommandLineRunner {

	@Autowired
	private NotificationScheduler notificationScheduler;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notificationScheduler.runNotifications();
	}


}
