package micro.mike.commons.converts;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ImageConverter implements AttributeConverter<String, String> {
    @Value("${host.images}")
    private String imagesPath;


    @Override
    public String convertToDatabaseColumn(String attribute) {
        return imagesPath.replaceAll(imagesPath, "");
    }

    //convert field when the attribute is got 
    @Override
    public String convertToEntityAttribute(String dbData) {
        return imagesPath.concat(dbData);
    }
}
