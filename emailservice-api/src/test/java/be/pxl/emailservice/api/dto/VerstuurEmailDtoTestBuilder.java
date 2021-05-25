package be.pxl.emailservice.api.dto;

import be.pxl.emailservice.test.infrastructure.util.Whitebox;

public class VerstuurEmailDtoTestBuilder {

    public static VerstuurEmailDtoTestBuilder aVerstuurEmailDto() {
        return new VerstuurEmailDtoTestBuilder();
    }
    
    private VerstuurEmailDtoTestBuilder() {
    }
    
    public VerstuurEmailDto build() {
        VerstuurEmailDto dto = Whitebox.createInstance(VerstuurEmailDto.class);
        //Whitebox.setInternalState(dto, "uuid", uuid);
        return dto;
    }
}
