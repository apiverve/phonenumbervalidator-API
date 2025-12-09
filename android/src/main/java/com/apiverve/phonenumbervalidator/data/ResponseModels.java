// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     PhoneNumberValidatorData data = Converter.fromJsonString(jsonString);

package com.apiverve.phonenumbervalidator.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static PhoneNumberValidatorData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(PhoneNumberValidatorData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(PhoneNumberValidatorData.class);
        writer = mapper.writerFor(PhoneNumberValidatorData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// PhoneNumberValidatorData.java

package com.apiverve.phonenumbervalidator.data;

import com.fasterxml.jackson.annotation.*;

public class PhoneNumberValidatorData {
    private String country;
    private String detectedCountry;
    private long countrycode;
    private long numberNational;
    private Object extension;
    private boolean isvalid;
    private String type;
    private Formatted formatted;

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("detectedCountry")
    public String getDetectedCountry() { return detectedCountry; }
    @JsonProperty("detectedCountry")
    public void setDetectedCountry(String value) { this.detectedCountry = value; }

    @JsonProperty("countrycode")
    public long getCountrycode() { return countrycode; }
    @JsonProperty("countrycode")
    public void setCountrycode(long value) { this.countrycode = value; }

    @JsonProperty("numberNational")
    public long getNumberNational() { return numberNational; }
    @JsonProperty("numberNational")
    public void setNumberNational(long value) { this.numberNational = value; }

    @JsonProperty("extension")
    public Object getExtension() { return extension; }
    @JsonProperty("extension")
    public void setExtension(Object value) { this.extension = value; }

    @JsonProperty("isvalid")
    public boolean getIsvalid() { return isvalid; }
    @JsonProperty("isvalid")
    public void setIsvalid(boolean value) { this.isvalid = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("formatted")
    public Formatted getFormatted() { return formatted; }
    @JsonProperty("formatted")
    public void setFormatted(Formatted value) { this.formatted = value; }
}

// Formatted.java

package com.apiverve.phonenumbervalidator.data;

import com.fasterxml.jackson.annotation.*;

public class Formatted {
    private String international;
    private String national;
    private String rfc;
    private String e164;

    @JsonProperty("international")
    public String getInternational() { return international; }
    @JsonProperty("international")
    public void setInternational(String value) { this.international = value; }

    @JsonProperty("national")
    public String getNational() { return national; }
    @JsonProperty("national")
    public void setNational(String value) { this.national = value; }

    @JsonProperty("rfc")
    public String getRFC() { return rfc; }
    @JsonProperty("rfc")
    public void setRFC(String value) { this.rfc = value; }

    @JsonProperty("e164")
    public String getE164() { return e164; }
    @JsonProperty("e164")
    public void setE164(String value) { this.e164 = value; }
}