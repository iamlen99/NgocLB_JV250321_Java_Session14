package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bus {
    private int id;
    private String licensePlate;
    private int capacity;
    private LocalDateTime joiningDate;

    public Bus() {
    }

    public Bus(int id, String licensePlate, int capacity, LocalDateTime joiningDate) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void inputData(Scanner scanner) {
        this.id = inputId(scanner);
        this.licensePlate = inputLicensePlate(scanner);
        this.capacity = inputCapacity(scanner);
        this.joiningDate = inputJoiningDate(scanner);
    }

    public int inputId(Scanner scanner) {
        do {
            try {
                System.out.print("Nhập ID xe: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("ID phải là số nguyên!");
            }
        } while (true);
    }

    public String inputLicensePlate(Scanner scanner) {
        do {
            System.out.print("Nhập biển số xe: ");
            String plate = scanner.nextLine();
            if (!plate.trim().isEmpty()) return plate;
            System.err.println("Biển số không được để trống.");
        } while (true);
    }

    public int inputCapacity(Scanner scanner) {
        do {
            try {
                System.out.print("Nhập sức chứa: ");
                int cap = Integer.parseInt(scanner.nextLine());
                if (cap > 0) return cap;
                System.err.println("Sức chứa phải lớn hơn 0.");
            } catch (Exception e) {
                System.err.println("Sức chứa phải là số nguyên!");
            }
        } while (true);
    }

    public LocalDateTime inputJoiningDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        do {
            try {
                System.out.print("Nhập ngày tham gia (dd-MM-yyyy HH:mm): ");
                return LocalDateTime.parse(scanner.nextLine(), formatter);
            } catch (Exception e) {
                System.err.println("Sai định dạng ngày!");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
