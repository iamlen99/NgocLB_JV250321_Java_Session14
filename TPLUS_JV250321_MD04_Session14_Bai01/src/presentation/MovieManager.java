package presentation;

import entity.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
    public static List<Movie> movieList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Chon chuc nang:");
            System.out.println("1. Them phim");
            System.out.println("2. Xoa phim");
            System.out.println("3. Sua phim");
            System.out.println("4. Hien thi phim");
            System.out.println("5. Tim kiem phim theo ten");
            System.out.println("6. Loc phim theo rating");
            System.out.println("7. Thoat");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addMovie(scanner);
                    break;

                case 2:
                    removeMovie(scanner);
                    break;

                case 3:
                    updateMovie(scanner);
                    break;

                case 4:
                    displayMovies();
                    break;

                case 5:
                    searchMovieByTitle(scanner);
                    break;

                case 6:
                    filterMoviesByRating(scanner);
                    break;

                case 7:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1-7");
            }
        } while (true);
    }

    public static void addMovie(Scanner scanner) {
        Movie movie = new Movie();
        movie.inputData(scanner);
        movieList.add(movie);
        System.out.println("Phim da duoc them thanh cong");
    }

    public static void removeMovie(Scanner scanner) {
        System.out.println("Nhap ID film can xoa:");
        int removeId = Integer.parseInt(scanner.nextLine());
        movieList.stream().filter(movie -> movie.getId() == removeId)
                .findFirst()
                .ifPresentOrElse(movieList::remove,
                        () -> {
                            System.out.println("Khong tim thay phim voi id vua nhap");
                        });
    }

    public static void updateMovie(Scanner scanner) {
        System.out.println("Nhap ID film can sua:");
        int updateId = Integer.parseInt(scanner.nextLine());
        movieList.stream().filter(movie -> movie.getId() == updateId)
                .findFirst()
                .ifPresentOrElse(movie -> {
                            String updatedMovie = movie.inputTitle(scanner);
                            movie.setTitle(updatedMovie);
                            String updatedDirector = movie.inputDirector(scanner);
                            movie.setDirector(updatedDirector);
                            LocalDate updatedReleaseDate = movie.inputReleaseDate(scanner);
                            movie.setReleaseDate(updatedReleaseDate);
                            Double updatedRating = movie.inputRating(scanner);
                            movie.setRating(updatedRating);
                            System.out.println("Cap nhat phim thanh cong");
                        },
                        () -> {
                            System.out.println("Khong tim thay phim voi id = " + updateId);
                        });
    }

    public static void displayMovies() {
        System.out.println("Danh sach phim:");
        movieList.forEach(System.out::println);
    }

    public static void searchMovieByTitle(Scanner scanner) {
        System.out.println("Nhap tieu de phim de tim kiem:");
        String searchTitle = scanner.nextLine();
        System.out.print("Phim tim thay: ");
        movieList.stream().filter(movie -> movie.getTitle().toLowerCase()
                .contains(searchTitle.toLowerCase())).forEach(System.out::println);
    }

    public static void filterMoviesByRating(Scanner scanner) {
        System.out.println("Nhap rating toi thieu de loc:");
        double minRating = Double.parseDouble(scanner.nextLine());
        System.out.println("Phim co rating lon hon " + minRating);
        movieList.stream().filter(movie -> movie.getRating() > minRating)
                .forEach(System.out::println);
    }
}
