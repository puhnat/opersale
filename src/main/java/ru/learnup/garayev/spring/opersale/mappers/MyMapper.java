package ru.learnup.garayev.spring.opersale.mappers;

import org.mapstruct.Mapper;
import ru.learnup.garayev.spring.opersale.controllers.dto.RealTheatrePremierDto;
import ru.learnup.garayev.spring.opersale.controllers.dto.SeasonDto;
import ru.learnup.garayev.spring.opersale.model.RealTheatrePremier;
import ru.learnup.garayev.spring.opersale.model.Season;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.entity.PremierEntity;

@Mapper(componentModel = "spring")
public interface MyMapper {
    SeasonDto toDto(Season season);
    Season toDomain(SeasonDto seasonDto);

    ListSeasonEntity toEntity(Season season);
    Season toDomain(ListSeasonEntity listSeasonEntity);

    //@Mappings({@Mapping(source = "realTheatrePremier.season.name", target = "season")})
    RealTheatrePremierDto toDto(RealTheatrePremier realTheatrePremier);
    default RealTheatrePremier toDomain(RealTheatrePremierDto theatrePremierDto){
        return new RealTheatrePremier(theatrePremierDto.getId(), theatrePremierDto.getName(), theatrePremierDto.getMemo(), theatrePremierDto.getAgeFrom(),
                theatrePremierDto.getCountPlace(), theatrePremierDto.getCountFreePlace(), theatrePremierDto.getDatePremier(), null);
    };

    PremierEntity toEntity(RealTheatrePremier premier);
    RealTheatrePremier toDomain(PremierEntity premierEntity);
}
