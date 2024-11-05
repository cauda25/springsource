package com.example.mart;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.mart.entity.cascade.Child;
import com.example.mart.entity.cascade.Parent;
import com.example.mart.repository.ChildRepositoty;
import com.example.mart.repository.ParentRepositoty;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryTest {
    @Autowired
    private ParentRepositoty parentRepositoty;

    @Autowired
    private ChildRepositoty childRepositoty;

    @Test
    public void insert() {
        Parent parent = Parent.builder()
                .name("이몽룡").build();

        LongStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("Child " + i).parent(parent).build();

            // parent.getChilds().add(child); // child 정보는 db에 추가되지 않음
            childRepositoty.save(child);
        });
        // parentRepositoty.save(parent);
    }

    @Test
    public void insert2() {
        Parent parent = Parent.builder()
                .name("이몽룡").build();

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().name("Child " + i).parent(parent).build();

            parent.getChilds().add(child);

        });
        parentRepositoty.save(parent);
    }

    @Test
    public void childRead() {
        // 자식2 정보 조회(+ 부모 조회)
        Child child = childRepositoty.findById(2L).get();
        System.out.println(child);
        System.out.println(child.getParent());

    }

    @Test
    public void testParentDelete() {
        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고
        parentRepositoty.deleteById(3L);

    }

    @Commit
    @Transactional
    @Test
    public void testParentDelete2() {
        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고
        Parent parent = parentRepositoty.findById(1L).get();
        // 자식만 삭제, 자식의 일부만 부모를 이용해서 삭제
        parent.getChilds().remove(0);
        // parent.getChilds().remove(1);
        // parent.getChilds().remove(2);
        parentRepositoty.save(parent);

    }

}
