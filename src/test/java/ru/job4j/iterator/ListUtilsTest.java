package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenAddAfterFirst() {
        ListUtils.addBefore(input, 0, 4);
        assertThat(input).hasSize(3).containsSequence(4, 1, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }

    @Test
    void whenRemoveIfEven() {
        input = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeIf(input, s -> s % 2 == 0);
        assertThat(input).hasSize(0).doesNotContainSequence(2, 4);
    }

    @Test
    void whenReplaceWithZeroIfNotEven() {
        ListUtils.replaceIf(input, s -> s % 2 != 0, 0);
        assertThat(input).hasSize(2).containsSequence(0, 0);
    }

    @Test
    void whenRemoveAllAndListIsEmpty() {
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).hasSize(0);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemovedAllAndOneElementIsLeft() {
        ListUtils.removeAll(input, Arrays.asList(5, 1));
        assertThat(input).hasSize(1).containsSequence(3);
    }
}