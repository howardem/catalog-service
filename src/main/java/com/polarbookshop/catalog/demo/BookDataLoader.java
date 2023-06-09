package com.polarbookshop.catalog.demo;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalog.domain.Book;
import com.polarbookshop.catalog.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Profile("testdata")
@Component
@RequiredArgsConstructor
public class BookDataLoader {

	private final BookRepository bookRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		this.bookRepository.deleteAll();
		
		Book book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Polarsophia");
		Book book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Polarsophia");

		this.bookRepository.saveAll(List.of(book1, book2));
	}

}
