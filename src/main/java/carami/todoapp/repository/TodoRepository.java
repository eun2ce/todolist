package carami.todoapp.repository;

import carami.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 기본 CRUD 메서드는 JpaRepository가 이미 제공 (save, findById, findAll, deleteById 등)
}
