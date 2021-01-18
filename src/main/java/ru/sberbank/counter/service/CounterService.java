package ru.sberbank.counter.service;

import org.springframework.stereotype.Service;
import ru.sberbank.counter.exception.CounterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CounterService {

    private final HashMap<String, Long> counter = new HashMap<>();

    public void create(String name) {
        counter.putIfAbsent(name, 0L);
    }

    public void increment(String name) throws CounterException {
        if (counter.get(name) > Long.MAX_VALUE) {
            throw new CounterException("Value range exceeded for counter: " + name);
        }
        counter.computeIfPresent(name, (k, v) -> v + 1);
    }

    public Long get(String name) {
        return counter.get(name);
    }

    public void remove(String name) {
        counter.remove(name);
    }

    public Long getTotalSum() {
        return counter.values().parallelStream().reduce(0L, (x, y) -> x + y);
    }

    public List<String> getCounterNameList() {
        return new ArrayList<>(counter.keySet());
    }
}
