package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenCheckIteratorWithOneElement() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(0);
        assertThat(set.iterator().hasNext()).isTrue();
        assertThat(set.iterator().next()).isEqualTo(0);
    }

    @Test
    void whenCheckIteratorWithNoElements() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThat(set.iterator().hasNext()).isFalse();
        assertThatThrownBy(() -> set.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenCheckThatElementIsOfParticularInstance() {
        SimpleSet<String> stringSet = new SimpleArraySet<>();
        stringSet.add("One");
        assertThat(stringSet.iterator().next()).isInstanceOf(String.class);
        SimpleSet<Integer> integerSet = new SimpleArraySet<>();
        integerSet.add(1);
        assertThat(integerSet.iterator().next()).isInstanceOf(Integer.class);
    }
}