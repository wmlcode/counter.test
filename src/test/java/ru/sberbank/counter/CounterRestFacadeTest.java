package ru.sberbank.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sberbank.counter.rest.CounterRestFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Safety Pig save my test forever
 * _._ _..._ .-',     _.._(`))
 *'-. `     '  /-._.-'    ',/
 *   )         \            '.
 *  / _    _    |             \
 * |  a    a    /              |
 * \   .-.                     ;
 *  '-('' ).-'       ,'       ;
 *      '-;           |      .'
 *         \           \    /
 *         | 7  .__  _.-\   \
 *         | |  |  ``/  /`  /
 *        /,_|  |   /,_/   /
 *           /,_/      '`-'
 */
@SpringBootTest
class CounterRestFacadeTest {

    private static ArrayList<String> counters = new ArrayList<>();

    private CounterRestFacade counterRestFacade;

    @Autowired
    void setCounterRestFacade(CounterRestFacade counterRestFacade) {
        this.counterRestFacade = counterRestFacade;
    }

    @BeforeAll
    static void before() {
        counters.add("counter1");
        counters.add("counter2");
        counters.add("counter3");
    }

    @Test
    void createTest() {
        counters.forEach(c -> counterRestFacade.create(c));
        List<String> counterList = counterRestFacade.getCounterNameList();
        System.out.println("List of counters: ");
        counterList.forEach(System.out::println);
    }

    @Test
    void increment() {
        Long sum = 0L;
        createTest();
        Random random = new Random();
        for (String counter : counters) {
            Integer incrementValue = random.nextInt(100 - 0);
            sum += incrementValue;
            for (int j = 0; j < incrementValue; j++) {
                counterRestFacade.increment(counter);
            }
        }
        Assertions.assertEquals(sum, counterRestFacade.getTotalSum());
    }

    @Test
    void get() {
        increment();
        Assertions.assertNotNull(counters.get(0));
    }

    @Test
    void remove() {
        createTest();
        int cnt = counters.size();
        counterRestFacade.remove(counters.get(0));
        Assertions.assertEquals(cnt - 1, counterRestFacade.getCounterNameList().size());
    }

}
