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
    void checkEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }
    @Test
    void checkNotContainSymbolEquals() {
        String name1 = "sum 100";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol \"=\"", name1);
    }
    @Test
    void checkNotContainKey() {
        String name1 = "=100";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key", name1);
    }
    @Test
    void checkNotContainValue() {
        String name1 = "sum=";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(name1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value", name1);
    }
}