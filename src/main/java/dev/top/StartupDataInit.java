package dev.top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.top.entities.Collegue;
import dev.top.repos.CollegueRepo;

@Component
public class StartupDataInit {

	@Autowired
	CollegueRepo collegueRepo;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		if (this.collegueRepo.count() <= 0) {
			this.collegueRepo.save(new Collegue("Banana", "Minion", "Bob", "Maison de Gru", 100,
					"https://vignette.wikia.nocookie.net/minions/images/0/04/Bob.jpg/revision/latest?cb=20150820153153&path-prefix=fr"));
			this.collegueRepo.save(new Collegue("Al", "Junkie", "Alice", "Terrier des lapins", 498,
					"https://images.pexels.com/photos/265036/pexels-photo-265036.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"));
			this.collegueRepo.save(new Collegue("Stu", "Minion", "Stuart", "Maison de Gru", 266,
					"https://images-na.ssl-images-amazon.com/images/I/810lcy2NBuL._SX355_.jpg"));
			this.collegueRepo.save(new Collegue("Kev", "Minion", "Kevin", "Maison de Gru", 465,
					"https://vignette.wikia.nocookie.net/minions/images/a/ab/Kevin.jpg/revision/latest?cb=20150820150450&path-prefix=fr"));
		}
	}
}
