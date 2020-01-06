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
//    app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		Actor actor = db.findActorById(3);
//		List<Actor> filmActors = db.findActorsByFilmId(4);
//		System.out.println(film);
//		System.out.println(actor);
//		System.out.println(filmActors);
//	}

	private void launch() throws SQLException {
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);

		sc.close();
	}

	private void startUserInterface(Scanner sc) throws SQLException {
		int i = 1;
		do {
			System.out.println("Enter a number to continue.\n" + "1 : Look up a film by its id.\n"
					+ "2 : Look up a film by a search keyword.\n" + "3 : Exit the application.");

			int userInput = sc.nextInt();

			if (userInput == 1) {
				int x = 1;
				do {
					System.out.println("Enter a film id number.");
					int userFilmId = sc.nextInt();
					Film film = db.findFilmById(userFilmId);
					Language language = db.findALanguageById(i);

					if (userFilmId > 1000) {
						System.out.println("That selection seems to be invalid.\nFilm Id range 1-1000.");
					} else {
						System.out.println("\nTitle\t     : " + film.getTitle() + "\nRelease Year : "
								+ film.getReleaseYear() + "\nRating\t     : " + film.getRating() + "\nLanguage     : "
								+ language.getName() + "\nDescription  : " + film.getDescription() + "\nActors\t     : "
								+ film.getActors() + "\n");
						x = 0;
					}
				} while (x > 0);

			} else if (userInput == 2) {
				System.out.println("Enter a search keyword.");
				String userKeyword = sc.next();
				List<Film> films = db.findFilmByKeyword(userKeyword);
				Language language = db.findALanguageById(i);

				films.forEach(film -> {
					System.out.println("\nTitle\t     : " + film.getTitle() + "\nRelease Year : "
							+ film.getReleaseYear() + "\nLanguage     : " + language.getName() + "\nRating\t     : "
							+ film.getRating() + "\nDescription  : " + film.getDescription() + "\nActors\t     : "
							+ film.getActors() + "\n");
				});

			} else if (userInput == 3) {
				System.out.println("3");
				i = 0;
			} else {
				System.out.println("Please enter a number between 1 & 3.");
			}

		} while (i > 0);
	}
}
