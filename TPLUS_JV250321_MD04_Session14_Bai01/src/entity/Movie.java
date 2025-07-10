package entity;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Movie {
    private int id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private double rating;

    public Movie(int id, String title, String director, LocalDate releaseDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Movie() {
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void inputData(Scanner scanner) {
        this.id = inputMovieId(scanner);
        this.title = inputTitle(scanner);
        this.director = inputDirector(scanner);
        this.releaseDate = inputReleaseDate(scanner);
        this.rating = inputRating(scanner);
    }

    public int inputMovieId(Scanner scanner) {
        System.out.println("Nhap ID phim: ");
        do {
            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.err.println("Vui long nhap ID la 1 so nguyen");
            }

        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        System.out.println("Nhap tieu de phim: ");
        do {
            String title = scanner.nextLine();
            if (isNotEmpty(title)) {
                return title;
            }
            System.err.println("Vui long nhap tieu de phim: ");
        } while (true);
    }

    public String inputDirector(Scanner scanner) {
        System.out.println("Nhap dao dien: ");
        do {
            String director = scanner.nextLine();
            if (isNotEmpty(director)) {
                return director;
            }
            System.err.println("Vui long nhap dao dien");
        } while (true);
    }

    public LocalDate inputReleaseDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nhap ngay phat hanh (dd/MM/yyyy):");
        do {
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (Exception e) {
                System.err.println("Ngay phat hanh phai co dinh dang dd/MM/yyyy, vui long nhap lai:");
            }
        } while (true);
    }

    public boolean isNotEmpty(String data) {
        return data != null && !data.trim().isEmpty();
    }

    public double inputRating(Scanner scanner) {
        System.out.println("Nhap rating:");
        do {
            try {
                double rating = Double.parseDouble(scanner.nextLine());
                if (rating >= 0 && rating <= 10) {
                    return rating;
                }
                System.err.println("Vui long nhap rating la 1 so thuc tu 0-10");
            } catch (Exception e) {
                System.err.println("Vui long nhap rating la 1 so thuc tu 0-10");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
}
