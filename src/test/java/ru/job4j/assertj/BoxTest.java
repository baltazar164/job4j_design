package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isMixedCase();
    }
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotEmpty()
                .isMixedCase();
    }
    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEmpty()
                .isMixedCase();
    }
    @Test
    void numberOfTetrahedronVertices() {
        List<Integer> cases = Arrays.asList(0, 4, 8);
        Box box = new Box(4, 6);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(4)
                .isPositive()
                .isIn(cases);
    }
    @Test
    void numberOfCubeVertices() {
        List<Integer> cases = Arrays.asList(0, 4, 8);
        Box box = new Box(8, 12);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(8)
                .isPositive()
                .isIn(cases);
    }
    @Test
    void tetrahedronIsExist() {
        Box box = new Box(4, 6);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue()
                .isEqualTo(true);
    }
    @Test
    void cubeIsExist() {
        Box box = new Box(8, 12);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue()
                .isEqualTo(true);
    }
    @Test
    void tetrahedronArea() {
        int edge = 6;
        Box box = new Box(4, edge);
        double a = edge;
        double area = box.getArea();
        assertThat(area).isCloseTo(Math.sqrt(3) * (a * a), withPrecision(0.01d))
                .isPositive();
    }
    @Test
    void cubeArea() {
        int edge = 12;
        Box box = new Box(8, edge);
        double a = edge;
        double area = box.getArea();
        assertThat(area).isCloseTo(6 * (a * a), withPrecision(0.01d))
                .isPositive();
    }
}