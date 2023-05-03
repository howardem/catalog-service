package com.polarbookshop.catalog.exception;

public class BookAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String isbn) {
		super("A book with ISBN " + isbn + " already exists.");
	}

}