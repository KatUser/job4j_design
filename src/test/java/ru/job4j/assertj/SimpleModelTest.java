package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleModelTest {
    @Test
    void  checkMessage() {
        SimpleModel simpleModel = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> simpleModel.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }
    @Test
    void  checkWordMessage() {
        SimpleModel simpleModel = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> simpleModel.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(word, number)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("name");
    }
}