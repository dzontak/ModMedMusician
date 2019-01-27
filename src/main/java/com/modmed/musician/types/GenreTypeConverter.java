package com.modmed.musician.types;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenreTypeConverter implements AttributeConverter<MusicGenre, String> {

    @Override
    public String convertToDatabaseColumn(MusicGenre composerType) {
        return composerType.getCode();
    }

    @Override
    public MusicGenre convertToEntityAttribute(String code) {
        return MusicGenre.fromCode(code);
    }
}
