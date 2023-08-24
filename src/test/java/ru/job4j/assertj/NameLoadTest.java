package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenArrayIsEmptyParseFails() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenHasNoEqualSignParseFails() {
        NameLoad nameLoad = new NameLoad();
        String name = "k-v";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain the symbol '='".formatted(name));
    }

    @Test
    void whenHasNoKeyParseFails() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain a key".formatted(name));
    }

    @Test
    void whenHasNoValueParseFails() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: %s does not contain a value".formatted(name));
    }
}