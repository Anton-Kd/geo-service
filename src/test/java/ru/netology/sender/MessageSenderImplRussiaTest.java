package ru.netology.sender;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;


class MessageSenderImplRussiaTest {

    @Test
    void send() {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.124.15.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);


        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.124.15.11");
        String result = messageSender.send(headers);
        assertEquals("Добро пожаловать", result);
    }

}