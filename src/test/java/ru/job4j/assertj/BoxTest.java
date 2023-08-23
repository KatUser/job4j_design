package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisUnknownObject() {
        Box box = new Box(0, -1);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Sphere")
                .containsAnyOf("u", "o", "c")
                .doesNotMatch("[^xyz]")
                .isEqualTo("Unknown object");
    }
    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .doesNotContainIgnoringCase("sphere", "tetrahedron")
                .isNotBlank()
                .isEqualTo("Cube");
    }
    @Test
    void whenVertexIsMinusOne() {
        Box box = new Box(0, -1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isNegative()
                .isLessThan(0)
                .isGreaterThan(-2)
                .isEqualTo(-1);
    }
    @Test
    void whenVertexIsZero() {
        Box box = new Box(0, 1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotEqualTo(1)
                .isNotIn(1, 2)
                .isEqualTo(0);
    }

    @Test
    void whenExists() {
        Box box = new Box(0, 1);
        boolean result = box.isExist();
        assertThat(result).isNotEqualTo(false)
                .isTrue();
    }
    @Test
    void whenDoesNotExist() {
        Box box = new Box(-1, 1);
        boolean result = box.isExist();
        assertThat(result).isNotEqualTo(true)
                .isEqualTo(false);
    }
    @Test
    void checkAreaOfSphere() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isGreaterThan(40.1d)
                .isLessThan(60d)
                .isCloseTo(50.26d, withPrecision(0.01d));
    }

    @Test
    void checkAreaOfTetrahedron() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isGreaterThanOrEqualTo(27d)
                .isBetween(26.99d, 28.01d)
                .isNotNegative()
                .isCloseTo(27.8d, Percentage.withPercentage(1.0d));
    }

    @Test
    void checkAreaOfCube() {
        Box box = new Box(8, 1);
        double area = box.getArea();
        assertThat(area).isNotNegative()
                .isGreaterThan(5.99d)
                .isCloseTo(5.98d, offset(0.3d))
                .isEqualTo(6d);
    }

    @Test
    void checkAreaOfDefault() {
        Box box = new Box(-1, 1);
        double area = box.getArea();
        assertThat(area).isLessThan(0.1d)
                .isEqualTo(0);
    }
}