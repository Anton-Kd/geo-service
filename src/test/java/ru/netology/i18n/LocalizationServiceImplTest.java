package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;


class LocalizationServiceImplTest {

    public static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of(Country.RUSSIA.toString(), "Добро пожаловать"),
                Arguments.of(Country.USA.toString(), "Welcome"),
                Arguments.of(Country.BRAZIL.toString(), "Welcome"),
                Arguments.of(Country.GERMANY.toString(), "Welcome")
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void localeCountry_Test(Country country, String expectedResponse) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actualResponse = localizationService.locale(country);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

}
