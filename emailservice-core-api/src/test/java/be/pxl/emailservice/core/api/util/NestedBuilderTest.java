package be.pxl.emailservice.core.api.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;

class NestedBuilderTest {

    @Test
    void builderCanBuildInstance() {
        Instance.Builder builder = new Instance.Builder();

        Instance actual = builder
            .withField("field")
            .build();

        assertThat(actual.field).isEqualTo("field");
        assertThat(actual.derivedField).isEqualTo("field");
    }

    @Test
    void builderCanBuildOnlyOnce() {
        Instance.Builder builder = new Instance.Builder();

        builder.build();

        assertThatIllegalStateException()
            .isThrownBy(builder::build);
    }

    @Test
    void cannotUseBuilderWhichHasBuilt() {
        Instance.Builder builder = new Instance.Builder();

        builder.build();

        assertThatIllegalStateException()
            .isThrownBy(() -> builder.withField("otherField"));
    }

    static class Instance {

        private String field;
        private String derivedField;

        static class Builder extends NestedBuilder<Instance> {

            @Override
            protected Instance createInstance() {
                return new Instance();
            }

            @Override
            protected void completeInstance() {
                instance().derivedField = instance().field;
            }

            public Builder withField(String field) {
                instance().field = field;
                return this;
            }
        }
    }

}
