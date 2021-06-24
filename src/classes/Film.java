package classes;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class specifies every film that will be used in this application.
 */
public class Film {
	// Attributes
	private String name;
	private String language;
	private String type;
	private int releasedDate;
	private double price;
	private String imgSrc;
	private Show show;
	private CinemaSalon salon;

	// Constructor
	public Film(String name,
				String language,
				String type,
				int releasedDate,
				double price,
				String imgSrc,
				Show show,
				CinemaSalon salon) {
		this.name = name;
		this.language = language;
		this.type = type;
		this.releasedDate = releasedDate;
		this.price = price;
		this.imgSrc = imgSrc;
		this.show = show;
		this.salon = salon;
	}

	// Methods
	/**
	 * This film returns a film object by the name which was sent to the method.
	 * @param filmName String
	 * @return Film
	 */
	public static Film returnSelectedFilm(String filmName) {
		Film film = null;

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all films in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				film = new Film(array[0], array[1], array[2],
						Integer.parseInt(array[3]),
						Double.parseDouble(array[4]),
						array[5], new Show(array[6], array[7], array[8]),
						new CinemaSalon(array[9]));

				// if film name matches with entered name than returns film
				if (film.getName().toLowerCase().equals(filmName.toLowerCase())) {
					return film;
				}
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}

	/**
	 * this method returns all film saved in the files.
	 * @return ArrayList<Film>
	 */
	public static ArrayList<Film> readFilms() {
		ArrayList<Film> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all lines in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// getting film info from formatted string
				Film film = new Film(array[0], array[1], array[2],
						Integer.parseInt(array[3]),
						Double.parseDouble(array[4]),
						array[5], new Show(array[6], array[7], array[8]),
						new CinemaSalon(array[9]));
				list.add(film);
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * this method returns a specific film according to name sent to method
	 * @param filmName String
	 * @return Film
	 */
	public static Film searchFilmsByName(String filmName) {
		Film film = null;

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all lines in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");

				// gets film if film name was matched with entered film name
				if (filmName.toLowerCase().equals(array[0].toLowerCase())) {
					// getting film info from formatted string
					film = new Film(array[0], array[1], array[2],
							Integer.parseInt(array[3]),
							Double.parseDouble(array[4]),
							array[5], new Show(array[6], array[7], array[8]),
							new CinemaSalon(array[9]));
				}
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}

	/**
	 * this method returns an Arraylist of films according to film type that was sent to method as argument.
	 * @param filmType String
	 * @return ArrayList<Film>
	 */
	public static ArrayList<Film> searchFilmsByType(String filmType) {
		ArrayList<Film> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all lines in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// gets film if film type was matched with entered film type
				if (filmType.toLowerCase().equals(array[2].toLowerCase())) {
					// getting film info from formatted string
					Film film = new Film(array[0], array[1], array[2],
							Integer.parseInt(array[3]),
							Double.parseDouble(array[4]),
							array[5], new Show(array[6], array[7], array[8]),
							new CinemaSalon(array[9]));
					list.add(film);
				}
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * this method returns an ArrayList of films according to film language that was sent to method as argument.
	 * @param filmLanguage String
	 * @return ArrayList<Film>
	 */
	public static ArrayList<Film> searchFilmsByLanguage(String filmLanguage) {
		ArrayList<Film> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all lines in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// gets film if film language was matched with entered film language
				if (filmLanguage.toLowerCase().equals(array[1].toLowerCase())) {
					// getting film info from formatted string
					Film film = new Film(array[0], array[1], array[2],
							Integer.parseInt(array[3]),
							Double.parseDouble(array[4]),
							array[5], new Show(array[6], array[7], array[8]),
							new CinemaSalon(array[9]));
					list.add(film);
				}
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * this method returns an ArrayList of films according to film released date that was sent to method as argument.
	 * @param filmReleasedDate String
	 * @return ArrayList<Film>
	 */
	public static ArrayList<Film> searchFilmsByReleasedDate(String filmReleasedDate) {
		ArrayList<Film> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);

			// reading all lines in file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// gets film if film released date was matched with entered film released date
				if (filmReleasedDate.toLowerCase().equals(array[3].toLowerCase())) {
					// getting film info from formatted string
					Film film = new Film(array[0], array[1], array[2],
							Integer.parseInt(array[3]),
							Double.parseDouble(array[4]),
							array[5], new Show(array[6], array[7], array[8]),
							new CinemaSalon(array[9]));
					list.add(film);
				}
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method adds a new film to file.
	 * @param film Film
	 */
	public static void addFilm(Film film) {
		try {
			// formatting film info to be written
			String toWrite = film.getName() + "," +
					film.getLanguage() + "," +
					film.getType() + "," +
					film.getReleasedDate() + "," +
					film.getPrice() + "," +
					film.getImgSrc() + "," +
					film.getShow().getShowDate() + "," +
					film.getShow().getStartTime() + "," +
					film.getShow().getEndTime() + "," +
					film.getSalon().getName();

			// opening file and adding film
			FileWriter fileWriter = new FileWriter("films.txt", true);
			fileWriter.write(toWrite + "\n");
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * this film checks all film information to be valid for adding in file
	 * @param film Film
	 * @return boolean
	 */
	public static boolean validateFilmForAddition(Film film) {
		boolean valid = true;
		ArrayList<Film> films = Film.readFilms();

		for (Film f : films) {
			// checking if film already exists in file and showing warning
			if (f.getName().equals(film.getName())) {
				valid = false;
				Alert alert = new Alert(Alert.AlertType.WARNING, film.getName() + " Film is already added to system");
				alert.show();
			}
			// checking if film show date, start time, end time and salon is same with other film
			// because showing to film in same salon and same time and date is forbidden and unlogical.
			if (f.getShow().getShowDate().equals(film.getShow().getShowDate()) &&
					f.getSalon().getName().equals(film.getSalon().getName()) &&
					(f.getShow().getStartTime().equals(film.getShow().getStartTime()) ||
							f.getShow().getEndTime().equals(film.getShow().getEndTime()))) {
				valid = false;
				Alert alert = new Alert(Alert.AlertType.WARNING, film.getShow().getShowDate() + " There is another film showed in the same date and time");
				alert.show();
			}
		}
		return valid;
	}

	/**
	 * this method deletes a film from file
	 * @param film Film
	 */
	public static void deleteFilm(Film film) {
		try {
			// opening file
			File file = new File("films.txt");
			Scanner reader = new Scanner(file);
			ArrayList<String> allLine = new ArrayList<>();

			// reading all films
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// if film name matches the entered film name it will be not added to arraylist
				if (array[0].equals(film.getName())) {
					continue;
				}
				// other all films are added to arraylist so that way we have all other film except the film
				// which we want to delete.
				else {
					allLine.add(line);
				}
			}
			// closing file
			reader.close();

			// opening file for writing
			FileWriter fileWriter = new FileWriter(file);

			// writing all films back to the file form ArrayList
			for (String element : allLine) {
				fileWriter.write(element + "\n");
			}
			// closing file
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters & Setters

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public CinemaSalon getSalon() {
		return salon;
	}

	public void setSalon(CinemaSalon salon) {
		this.salon = salon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(int releasedDate) {
		this.releasedDate = releasedDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
}
