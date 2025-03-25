package carami.todoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // 할 일 내용
    private boolean completed;  // 완료 여부
    private LocalDateTime created;
    // 샘플 데이터 편의를 위해 간단한 생성자 오버로드
    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    /**
     * 엔티티가 처음 저장(Persist)될 때, created가 null이라면 현재 시각으로 채움
     */
    @PrePersist
    public void onPrePersist() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
    }
}
