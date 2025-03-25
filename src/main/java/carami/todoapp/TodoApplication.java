package carami.todoapp;

import carami.todoapp.entity.Todo;
import carami.todoapp.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    /**
     * 앱 실행 시, DB에 ToDo가 하나도 없으면 샘플 데이터를 저장하는 로직
     */
    @Bean
    public CommandLineRunner dataInitializer(TodoRepository todoRepository) {
        return args -> {
            long count = todoRepository.count();
            if (count == 0) {
                // DB에 아무 데이터도 없을 경우, 예시 데이터 3건 추가
                todoRepository.save(new Todo("일기쓰기", false));
                todoRepository.save(new Todo("Docker 공부하기", false));
                todoRepository.save(new Todo("산책하기", true));
                System.out.println("=== 샘플 ToDo 데이터 삽입 ===");
            } else {
                System.out.println("=== 이미 ToDo 데이터가 존재합니다. 삽입 생략 ===");
            }
        };
    }
}
