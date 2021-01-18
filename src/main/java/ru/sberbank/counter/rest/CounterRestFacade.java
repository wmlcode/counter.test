package ru.sberbank.counter.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.counter.exception.CounterException;
import ru.sberbank.counter.service.CounterService;

import java.util.List;

@RestController
@RequestMapping(path = "/counter")
@Slf4j
public class CounterRestFacade {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping(path = "/create/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("name") String name) {
        counterService.create(name);
    }

    @PutMapping(path = "/increment/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void increment(@PathVariable("name") String name) {
        try {
            counterService.increment(name);
        } catch (CounterException e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping(path = "/get/{name}")
    public Long get(@PathVariable("name") String name) {
        return counterService.get(name);
    }

    @DeleteMapping(path = "/remove/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("name") String name) {
        counterService.remove(name);
    }

    @GetMapping(path = "/total")
    public Long getTotalSum() {
        return counterService.getTotalSum();
    }

    @GetMapping(path = "/list")
    public List<String> getCounterNameList() {
        return counterService.getCounterNameList();
    }
}
