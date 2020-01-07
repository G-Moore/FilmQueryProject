package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);

		sc.close();
	}

	private void filmById(Scanner sc) throws SQLException {
		int x = 1;
		int i = 1;
		do {
			try {
			System.out.println("Enter a film id number.");
			int userFilmId = sc.nextInt();
			Film film = db.findFilmById(userFilmId);
			Language language = db.findALanguageById(i);
				if (film.getTitle().length() > 0) {
					System.out.println("\nTitle\t     : " + film.getTitle() + "\nRelease Year : "
							+ film.getReleaseYear() + "\nRating\t     : " + film.getRating() + "\nLanguage     : "
							+ language.getName() + "\nDescription  : " + film.getDescription() + "\nActors\t     : "
							+ film.getActors() + "\n");
					x = 0;
				}
			} catch (Exception e) {
				System.out.println("That selection seems to be invalid.\nFilm Id range 1-1000.");
				x = 0;
			}
		} while (x > 0);
	}

	private void filmByKeyword(Scanner sc) throws SQLException {
		int i = 1;
		System.out.println("Enter a search keyword.");
		String userKeyword = sc.next();
		List<Film> films = db.findFilmByKeyword(userKeyword);
		Language language = db.findALanguageById(i);
		if (films.isEmpty()) {
			System.out.println("No results found.");
		} else {
			films.forEach(film -> {
				System.out.println("\nTitle\t     : " + film.getTitle() + "\nRelease Year : " + film.getReleaseYear()
						+ "\nLanguage     : " + language.getName() + "\nRating\t     : " + film.getRating()
						+ "\nDescription  : " + film.getDescription() + "\nActors\t     : " + film.getActors() + "\n");
			});
		}
	}

	private void startUserInterface(Scanner sc) throws SQLException {
		int i = 1;
		do {
			System.out.println("Enter a number to continue.\n" + "1 : Look up a film by its id.\n"
					+ "2 : Look up a film by a search keyword.\n" + "3 : Exit the application.");
			try {
			int userInput = sc.nextInt();

			if (userInput == 1) {
				filmById(sc);
			} else if (userInput == 2) {
				filmByKeyword(sc);
			} else if (userInput == 3) {
				System.out.println("GoodBye. Thanks for using the Film Query App.");
				i = 0;
			} else {
				System.out.println("Please enter a number between 1 & 3.");
			}
			}catch (Exception e) {
				System.out.println("That selection seems to be invalid.");
				i = 0;
			}
		} while (i > 0);

	}
}
