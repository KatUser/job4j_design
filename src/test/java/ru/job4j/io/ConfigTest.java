package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenKeyIsOkAndValueIsOk() {
        String path = "./data/test_key_value_ok.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"))
                .isEqualTo("busya");
    }

    @Test
    void whenKeyIsOkAndValueIsNotOk() {
        String path = "./data/test_key_ok_value_not_ok.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileIsEmpty() {
        String path = "./data/test_when_empty_file.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileIsEmptyWithComment() {
        String path = "./data/test_when_empty_file_with_comment.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenLineContainsMoreThanOneEqualAndValueAfter() {
        String path = "./data/when_key_value_with_extra_equal_and_value_after.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"))
                .isEqualTo("value=1");
    }

    @Test
    void whenLineContainsMoreThanOneEqual() {
        String path = "./data/when_key_value_with_extra_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"))
                .isEqualTo("value=");
    }

    @Test
    void whenFileContainsEqualOnly() {
        String path = "./data/test_when_file_contains_equal_only.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}