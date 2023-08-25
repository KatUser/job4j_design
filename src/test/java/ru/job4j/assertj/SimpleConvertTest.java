package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;

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
    void checkArraySizeOne() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("cat");
        assertThat(array).isNotEmpty()
                .hasSize(1)
                .doesNotContainSequence("dog")
                .doesNotContain("owl", Index.atIndex(0))
                .containsAnyOf("cat", "cow");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("apple", "apricot");
        assertThat(list).hasSize(2)
                .allSatisfy(s -> {
                    assertThat(s.length()).isGreaterThan(1);
                    assertThat(s.length()).isLessThan(10);
                })
                .containsAnyOf("watermelon", "apple", "banana")
                .allMatch(s -> s.startsWith("a"))
                .noneMatch(s -> s.contains("y"))
                .anyMatch(s -> s.endsWith("ot"));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("java", "python", "assembler", "basic");
        assertThat(set).isNotEmpty();
        assertThat(set).first().isInstanceOf(String.class);
        assertThat(set).isNotEmpty();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("2", "4", "6");
        assertThat(map).containsKeys("2", "4", "6")
                .containsValues(0, 1, 2)
                .doesNotContainKey("0")
                .doesNotContainValue(3)
                .containsEntry("4", 1)
                .doesNotContainEntry("5", 3)
                .hasSize(3);
    }
}