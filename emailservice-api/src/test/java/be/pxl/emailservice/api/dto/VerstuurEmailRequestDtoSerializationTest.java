package be.pxl.emailservice.api.dto;

import static be.pxl.emailservice.api.dto.VerstuurEmailDtoRequestTestBuilder.aVerstuurEmailRequest;

import be.pxl.emailservice.infrastructure.util.SerializationTest;

@SuppressWarnings("squid:S2187")
class VerstuurEmailRequestDtoSerializationTest extends SerializationTest<VerstuurEmailRequestDto> {

    @Override
    protected VerstuurEmailRequestDto createInstance() {
        return aVerstuurEmailRequest().build();
    }
}
