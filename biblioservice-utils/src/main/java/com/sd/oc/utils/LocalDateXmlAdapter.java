package com.sd.oc.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(formatter);
    }

    @Override
    public LocalDate unmarshal(String localDateString) throws Exception {
            return LocalDate.parse(localDateString, formatter);
    }
}
