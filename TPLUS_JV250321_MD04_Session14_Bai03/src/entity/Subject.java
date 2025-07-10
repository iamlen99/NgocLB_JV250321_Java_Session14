package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Subject {
    private String code;
    private String name;
    private int credits;
    private LocalDate startDate;

    public Subject() {}

    public Subject(String code, String name, int credits, LocalDate startDate) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void inputData(Scanner scanner) {
        this.code = inputCode(scanner);
        this.name = inputName(scanner);
        this.credits = inputCredits(scanner);
        this.startDate = inputStartDate(scanner);
    }

    public String inputCode(Scanner scanner) {
        do {
            System.out.print("Nhập mã môn học: ");
            String code = scanner.nextLine();
            if (isNotEmpty(code)) return code;
            System.err.println("Mã không được để trống.");
        } while (true);
    }

    public String inputName(Scanner scanner) {
        do {
            System.out.print("Nhập tên môn học: ");
            String name = scanner.nextLine();
            if (isNotEmpty(name)) return name;
            System.err.println("Tên không được để trống.");
        } while (true);
    }

    public int inputCredits(Scanner scanner) {
        do {
            try {
                System.out.print("Nhập số tín chỉ: ");
                int credits = Integer.parseInt(scanner.nextLine());
                if (credits > 0) return credits;
                System.err.println("Tín chỉ phải lớn hơn 0.");
            } catch (Exception e) {
                System.err.println("Tín chỉ phải là số nguyên.");
            }
        } while (true);
    }

    public LocalDate inputStartDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            try {
                System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (Exception e) {
                System.err.println("Sai định dạng ngày!");
            }
        } while (true);
    }

    public boolean isNotEmpty(String data) {
        return data != null && !data.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Subject{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", startDate=" + startDate +
                '}';
    }
}

