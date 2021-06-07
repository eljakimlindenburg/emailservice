package be.pxl.emailservice.core.api.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EqualsByStateObjectTest {

    @Test
    void testEquals() {
        String value = "value";
        TestValueObject1 testValueObject11 = new TestValueObject1(value);
        TestValueObject1 testValueObject12 = new TestValueObject1(value);
        TestValueObject1 testValueObject13 = new TestValueObject1("anothervalue");
        TestValueObject2 testValueObject21 = new TestValueObject2(value);

        assertThat(testValueObject11)
            .isEqualTo(testValueObject11)
            .isEqualTo(testValueObject12)
            .isNotEqualTo(testValueObject13)
            .isNotEqualTo(testValueObject21);
    }

    @Test
    void testHashcode() {
        String value = "value";
        TestValueObject1 testValueObject11 = new TestValueObject1(value);
        TestValueObject1 testValueObject12 = new TestValueObject1(value);
        TestValueObject1 testValueObject13 = new TestValueObject1("anothervalue");

        assertThat(testValueObject11).hasSameHashCodeAs(testValueObject11)
            .hasSameHashCodeAs(testValueObject12);
        assertThat(testValueObject11.hashCode()).isNotEqualTo(testValueObject13.hashCode());
    }

    @Test
    void testToString() {
        String value = "value";
        TestValueObject1 testValueObject11 = new TestValueObject1(value);
        TestValueObject1 testValueObject12 = new TestValueObject1(value);
        TestValueObject1 testValueObject13 = new TestValueObject1("anothervalue");
        TestValueObject2 testValueObject21 = new TestValueObject2(value);

        assertThat(testValueObject11)
            .hasToString(testValueObject11.toString())
            .hasToString(testValueObject12.toString());
        assertThat(testValueObject11.toString()).isNotEqualTo(testValueObject13.toString());
        assertThat(testValueObject11.toString()).isNotEqualTo(testValueObject21.toString());
    }

    private static class TestValueObject1 extends EqualsByStateObject {

        private String value;

        public TestValueObject1(String value) {
            this.value = value;
        }
    }

    private static class TestValueObject2 extends EqualsByStateObject {

        private String value;

        public TestValueObject2(String value) {
            this.value = value;
        }
    }

}
