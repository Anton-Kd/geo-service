package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;


class GeoServiceTest {


    public static Stream<Arguments> factory() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("factory")
    void byIp(String ip, Location expected) {

        GeoService geoService = new GeoServiceImpl();
        Location response = geoService.byIp(ip);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expected.getCountry(), response.getCountry()),
                () -> Assertions.assertEquals(expected.getCity(), response.getCity()),
                () -> Assertions.assertEquals(expected.getStreet(), response.getStreet()),
                () -> Assertions.assertEquals(expected.getBuiling(), response.getBuiling())
        );
    }
}