package ru.fds.hrdepartmentmonitoring.batch.step.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.Cat;

import java.util.ArrayDeque;
import java.util.Queue;

@Slf4j
@Component
@Qualifier("catReader")
public class CatReader implements ItemReader<Cat> {
    private static final Queue<Cat> cats = new ArrayDeque<>();

    static {
        for(int i = 0; i < 50; i++){
            cats.add(new Cat((long) i, "name"+i, "breed"+i));
        }
    }


    @Override
    public Cat read() {
        return cats.poll();
    }
}
