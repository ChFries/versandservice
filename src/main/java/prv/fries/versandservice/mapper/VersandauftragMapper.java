package prv.fries.versandservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import prv.fries.versandservice.entity.Versandauftrag;
import prv.fries.versandservice.generated.VersandauftragDto;
import prv.fries.versandservice.model.VersandStatus;

@Mapper(componentModel = "spring")
public interface VersandauftragMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="status", source="versandStatus")
    @Mapping(target="kundenId", source="kundenId")
    @Mapping(target="bestellungId", source="bestellungId")
    @Mapping(target="sendungsnummer", source="sendungsnummer")
    VersandauftragDto toDto(Versandauftrag versandauftrag);

    @ValueMapping(target="VERSENDET", source="VERSENDET")
    @ValueMapping(target="ERFASST", source="ERFASST")
    VersandauftragDto.StatusEnum toStatusEnum(VersandStatus versandStatus );

}
