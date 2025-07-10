import entity.Subject;
import presentation.SubjectManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SubjectManager<Subject> manager = new SubjectManager<>();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nCHỌN CHỨC NĂNG:");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Xoá môn học");
            System.out.println("3. Hiển thị danh sách môn học");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học có tín chỉ > 3");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Subject subject = new Subject();
                    subject.inputData(scanner);
                    manager.addSubject(subject);
                    break;

                case 2:
                    System.out.print("Nhập mã môn học cần xoá: ");
                    String code = scanner.nextLine();
                    manager.removeSubject(code);
                    break;

                case 3:
                    manager.displaySubjects();
                    break;

                case 4:
                    System.out.print("Nhập tên môn học cần tìm: ");
                    String name = scanner.nextLine();
                    manager.searchByName(name);
                    break;

                case 5:
                    manager.filterByCredits(3);
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.err.println("Chỉ chọn từ 1 - 6.");
            }
        } while (true);
    }
}