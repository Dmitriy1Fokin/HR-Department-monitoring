package ru.fds.hrdepartmentmonitoring.batch.step.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.Cat;

@Component
@Qualifier("catProcessor")
public class CatProcessor implements ItemProcessor<Cat, Cat> {
    @Override
    public Cat process(Cat item) {
        return new Cat(item.getId()+100, item.getName()+100, item.getBreed()+100);
    }
}
