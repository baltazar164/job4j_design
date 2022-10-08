package ru.job4j.assertj;

import com.sun.jdi.Value;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.*;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }
    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }
    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("seven");
    }
    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> actualMap = simpleConvert.toMap("first", "second", "three", "four", "five");
        Map<String, Integer> expectedMap = Map.of("second", 1, "four", 3);
        assertThat(actualMap).containsAllEntriesOf(expectedMap);
    }
}