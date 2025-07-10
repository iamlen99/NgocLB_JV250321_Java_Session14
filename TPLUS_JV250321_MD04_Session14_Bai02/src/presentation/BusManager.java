package presentation;

import entity.Bus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BusManager {
    public static List<Bus> busList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nCHỌN CHỨC NĂNG:");
            System.out.println("1. Thêm xe");
            System.out.println("2. Xoá xe");
            System.out.println("3. Sửa xe");
            System.out.println("4. Hiển thị danh sách xe");
            System.out.println("5. Tìm kiếm theo biển số");
            System.out.println("6. Lọc theo ngày tham gia");
            System.out.println("7. Thoát");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addBus(scanner);
                    break;
                case 2:
                    removeBus(scanner);
                    break;
                case 3:
                    updateBus(scanner);
                    break;
                case 4:
                    displayBusList();
                    break;
                case 5:
                    searchByLicense(scanner);
                    break;
                case 6:
                    filterByJoiningDate(scanner);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Chọn từ 1 - 7.");
            }
        } while (true);
    }

    public static void addBus(Scanner scanner) {
        Bus bus = new Bus();
        bus.inputData(scanner);

        boolean exists = busList.stream().anyMatch(b -> b.getId() == bus.getId());
        if (exists) {
            System.out.println("ID đã tồn tại. Không thể thêm.");
        } else {
            busList.add(bus);
            System.out.println("Thêm xe thành công.");
        }
    }

    public static void removeBus(Scanner scanner) {
        System.out.print("Nhập ID xe cần xoá: ");
        int id = Integer.parseInt(scanner.nextLine());
        busList.stream().filter(b -> b.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        bus -> {
                            busList.remove(bus);
                            System.out.println("Đã xoá xe.");
                        },
                        () -> System.out.println("Không tìm thấy xe.")
                );
    }

    public static void updateBus(Scanner scanner) {
        System.out.print("Nhập ID xe cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Bus> optionalBus = busList.stream().filter(b -> b.getId() == id).findFirst();

        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            System.out.println("Nhập thông tin mới:");
            String newPlate = bus.inputLicensePlate(scanner);
            int newCapacity = bus.inputCapacity(scanner);
            bus.setLicensePlate(newPlate);
            bus.setCapacity(newCapacity);
            bus.setJoiningDate(bus.inputJoiningDate(scanner));
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy xe.");
        }
    }

    public static void displayBusList() {
        if (busList.isEmpty()) {
            System.out.println("Danh sách xe trống.");
            return;
        }

        int totalCapacity = busList.stream().mapToInt(Bus::getCapacity).sum();

        System.out.println("===== DANH SÁCH XE =====");
        busList.forEach(System.out::println);
        System.out.println("Tổng số chỗ: " + totalCapacity);
    }

    public static void searchByLicense(Scanner scanner) {
        System.out.print("Nhập biển số để tìm: ");
        String plate = scanner.nextLine();

        busList.stream()
                .filter(b -> b.getLicensePlate().equalsIgnoreCase(plate))
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Không tìm thấy xe.")
                );
    }

    public static void filterByJoiningDate(Scanner scanner) {
        System.out.print("Nhập ngày cần lọc (dd-MM-yyyy): ");
        String dateStr = scanner.nextLine();
        try {
            LocalDate targetDate = LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            List<Bus> filtered = busList.stream()
                    .filter(bus -> bus.getJoiningDate().toLocalDate().equals(targetDate))
                    .toList();

            if (filtered.isEmpty()) {
                System.out.println("Không có xe tham gia vào ngày này.");
            } else {
                System.out.println("Xe tham gia ngày " + targetDate + ":");
                filtered.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Ngày không hợp lệ.");
        }
    }
}

