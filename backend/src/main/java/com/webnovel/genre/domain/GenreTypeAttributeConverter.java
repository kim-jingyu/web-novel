package com.webnovel.genre.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenreTypeAttributeConverter implements AttributeConverter<String, Integer> {
    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if(attribute.equals("ACTION") || attribute.equals("1")){
            return 1;
        }else if(attribute.equals("ROMANCE") || attribute.equals("2")){
            return 2;
        }else if(attribute.equals("FANTASY") || attribute.equals("3")){
            return 3;
        }
        return 1000;
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if(dbData ==1){
            return "ACTION";
        }else if(dbData == 2){
            return "ROMANCE";
        }else if(dbData == 3){
            return "FANTASY";
        }
        return "";
    }
}
