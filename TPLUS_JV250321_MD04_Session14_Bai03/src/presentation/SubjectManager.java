package presentation;

import entity.Subject;

import java.util.*;
import java.util.stream.Collectors;

public class SubjectManager<T extends Subject> {
    private List<T> subjectList = new ArrayList<>();

    public void addSubject(T subject) {
        boolean exists = subjectList.stream().anyMatch(s -> s.getCode().equalsIgnoreCase(subject.getCode()));
        if (exists) {
            System.out.println("Mã môn học đã tồn tại.");
        } else {
            subjectList.add(subject);
            System.out.println("Thêm môn học thành công.");
        }
    }

    public void removeSubject(String code) {
        Optional<T> subjectOpt = subjectList.stream()
                .filter(s -> s.getCode().equalsIgnoreCase(code)).findFirst();
        subjectOpt.ifPresentOrElse(
                subject -> {
                    subjectList.remove(subject);
                    System.out.println("Đã xoá môn học.");
                },
                () -> System.out.println("Không tìm thấy môn học.")
        );
    }

    public void displaySubjects() {
        if (subjectList.isEmpty()) {
            System.out.println("Danh sách môn học trống.");
        } else {
            subjectList.forEach(System.out::println);
        }
    }

    public void searchByName(String name) {
        List<T> results = subjectList.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy môn học.");
        } else {
            results.forEach(System.out::println);
        }
    }

    public void filterByCredits(int minCredits) {
        List<T> filtered = subjectList.stream()
                .filter(s -> s.getCredits() > minCredits)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Không có môn học nào thỏa điều kiện.");
        } else {
            filtered.forEach(System.out::println);
        }
    }
}

