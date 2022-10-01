package com.jakubeeee.misc;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.requireNonNull;

final class CsvValidator implements ConstraintValidator<CsvFile, MultipartFile> {

    private static final String VALID_EXTENSION = "csv";
    private static final String VALID_CONTENT_TYPE = "text/csv";

    @Override
    public boolean isValid(MultipartFile multipart, ConstraintValidatorContext context) {
        return isExtensionValid(multipart) && isContentTypeValid(multipart);
    }

    private boolean isExtensionValid(MultipartFile multipart) {
        var fileName = requireNonNull(multipart.getOriginalFilename());
        var extension = fileName.substring(fileName.indexOf('.') + 1);
        return VALID_EXTENSION.equals(extension);
    }

    private boolean isContentTypeValid(MultipartFile multipart) {
        var contentType = multipart.getContentType();
        return VALID_CONTENT_TYPE.equals(contentType);
    }

}
