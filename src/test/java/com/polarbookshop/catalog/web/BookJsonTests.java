package com.polarbookshop.catalog.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import com.polarbookshop.catalog.domain.Book;

@JsonTest
class BookJsonTests {

	@Autowired
	private JacksonTester<Book> json;

	@Test
	void testSerialize() throws Exception {
		Book book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
		JsonContent<Book> jsonContent = json.write(book);

		assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
			.isEqualTo(book.isbn());
		assertThat(jsonContent).extractingJsonPathStringValue("@.title")
			.isEqualTo(book.title());
		assertThat(jsonContent).extractingJsonPathStringValue("@.author")
			.isEqualTo(book.author());
		assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
			.isEqualTo(book.price());
	}

	@Test
	void testDeserialize() throws Exception {
		String content = """
			{
				"isbn": "1234567890",
				"title": "Title",
				"author": "Author",
				"price": 9.90
			}
			""";

		assertThat(json.parse(content))
			.usingRecursiveComparison()
			.isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia"));
	}
}