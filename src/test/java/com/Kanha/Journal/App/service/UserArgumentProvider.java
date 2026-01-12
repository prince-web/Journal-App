package com.Kanha.Journal.App.service;

import com.Kanha.Journal.App.entity.UserEntry;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(UserEntry.builder().userName("Shyam").password("Shyam").build()),
                Arguments.of(UserEntry.builder().userName("Suraj").password("Suraj").build())

        );
    }
}
