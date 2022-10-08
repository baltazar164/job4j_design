package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next() == 1);
        assertThat(it.next() == 2);
        assertThat(it.next() == 3);
    }
}