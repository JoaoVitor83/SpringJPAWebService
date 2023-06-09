package com.embosoft.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.embosoft.course.entities.Category;
import com.embosoft.course.entities.Order;
import com.embosoft.course.entities.Product;
import com.embosoft.course.entities.User;
import com.embosoft.course.entities.enums.OrderStatus;
import com.embosoft.course.repositories.CategoryRepository;
import com.embosoft.course.repositories.OrderRepository;
import com.embosoft.course.repositories.ProductRepository;
import com.embosoft.course.repositories.UserRepository;

@Configuration // Específica que essa classe é uma classe de configuração
@Profile("test") // Identifica o usuário que vai receber determinada configuração
public class TestConfig implements CommandLineRunner { // Interface CommandLineRunner serve para colocar pontos de
														// execução na nossa aplicação

	@Autowired // Automatiza a injeção de dependência
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {

		Category c1 = new Category(null, "Eletrônicos");
		Category c2 = new Category(null, "Periféricos");
		categoryRepository.saveAll(Arrays.asList(c1, c2));

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u2);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		p1.getCategories().add(c2);
		p2.getCategories().add(c1);
		p3.getCategories().add(c2);
		p4.getCategories().add(c1);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

	}
}
