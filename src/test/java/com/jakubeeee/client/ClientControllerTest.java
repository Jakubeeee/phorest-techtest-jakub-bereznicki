package com.jakubeeee.client;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.jakubeeee.client.ClientControllerTestConstants.*;
import static com.jakubeeee.client.Gender.MALE;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
final class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Nested
    final class ClientSavingTest {
        private static final String ENDPOINT_URL = "/client/save";

        @Test
        void shouldReturn200_whenSavingValidClient() throws Exception {
            // WHEN
            var result = mockMvc.perform(post(ENDPOINT_URL)
                    .contentType(APPLICATION_JSON)
                    .content(VALID_CLIENT)
            );
            // THEN
            result.andExpect(status().isOk());
        }

        @ParameterizedTest()
        @ValueSource(strings = {
                INVALID_CLIENT_MISSING_IDENTIFIER,
                INVALID_CLIENT_BLANK_IDENTIFIER,
                INVALID_CLIENT_MISSING_FIRST_NAME,
                INVALID_CLIENT_BLANK_FIRST_NAME,
                INVALID_CLIENT_MISSING_LAST_NAME,
                INVALID_CLIENT_BLANK_LAST_NAME,
                INVALID_CLIENT_MISSING_EMAIL,
                INVALID_CLIENT_BLANK_EMAIL,
                INVALID_CLIENT_NOT_WELL_FORMED_EMAIL,
                INVALID_CLIENT_MISSING_PHONE,
                INVALID_CLIENT_BLANK_PHONE,
                INVALID_CLIENT_MISSING_GENDER,
                INVALID_CLIENT_UNSUPPORTED_GENDER,
                INVALID_CLIENT_MISSING_BANNED,
                INVALID_CLIENT_UNSUPPORTED_BANNED
        })
        void shouldReturn400_whenSavingInvalidClient(String client) throws Exception {
            // WHEN
            var result = mockMvc.perform(post(ENDPOINT_URL)
                    .contentType(APPLICATION_JSON)
                    .content(client)
            );
            // THEN
            result.andExpect(status().isBadRequest());
        }

    }

    @Nested
    final class ClientBulkSavingTest {
        // TODO
    }

    @Nested
    final class ClientFetchingTest {
        private static final String ENDPOINT_URL = "/client/fetch/";

        @Test
        void shouldReturnClient_whenFetchingWithProperIdentifier() throws Exception {
            // GIVEN
            var identifier = "test_identifier";
            given(service.fetch(identifier)).willReturn(entity());

            // WHEN
            var result = mockMvc.perform(
                    get(ENDPOINT_URL + identifier)
            );

            // THEN
            result.andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(content().json(VALID_CLIENT, true));
        }

        @Test
        void shouldReturn404_whenFetchingWithoutIdentifier() throws Exception {
            // WHEN
            var result = mockMvc.perform(get(ENDPOINT_URL));

            // THEN
            result.andExpect(status().isNotFound());
        }

        private ClientEntity entity() {
            return new ClientEntity(
                    "test_identifier",
                    "test_first_name",
                    "test_last_name",
                    "test.email@mail.com",
                    "123456789",
                    MALE,
                    false);
        }
    }

    @Nested
    final class TopClientsFetchingTest {
        // TODO
    }

    @Nested
    final class ClientUpdatingTest {
        // TODO
    }

    @Nested
    final class ClientDeletingTest {
        private static final String ENDPOINT_URL = "/client/delete/";

        @Test
        void shouldReturn200_whenDeletingWithProperIdentifier() throws Exception {
            // GIVEN
            var identifier = "test_identifier";

            // WHEN
            var result = mockMvc.perform(
                    delete(ENDPOINT_URL + identifier)
            );

            // THEN
            result.andExpect(status().isOk());
        }

        @Test
        void shouldReturn404_whenDeletingWithoutIdentifier() throws Exception {
            // WHEN
            var result = mockMvc.perform(delete(ENDPOINT_URL));

            // THEN
            result.andExpect(status().isNotFound());
        }
    }

}
