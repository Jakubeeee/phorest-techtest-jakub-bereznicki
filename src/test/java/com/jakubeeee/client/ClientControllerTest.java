package com.jakubeeee.client;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static com.jakubeeee.client.ClientControllerTestConstants.*;
import static com.jakubeeee.client.Gender.FEMALE;
import static com.jakubeeee.client.Gender.MALE;
import static com.jakubeeee.tools.FileTools.readFile;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
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
        private static final String ENDPOINT_URL = "/client/bulkSave";
        private static final String TEST_FILE_NAME = "valid_bulk_save_clients.csv";
        private static final String TEST_FILE_NAME_WRONG_EXTENSION = "invalid_bulk_save_clients_wrong_extension.notcsv";
        private static final String TEST_FILE_MULTIPART_NAME = "file";
        private static final String TEST_FILE_CONTENT_TYPE = "text/csv";
        private static final String TEST_FILE_NAME_WRONG_CONTENT_TYPE = "text/notcsv";

        @Test
        void shouldReturn200_whenBulkSavingClientsWithValidFile() throws Exception {
            // GIVEN
            var file = readFile(ClientControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform(multipart(ENDPOINT_URL).file(multipartFile));
            // THEN
            result.andExpect(status().isOk());
        }

        @Test
        void shouldReturn400_whenBulkSavingClientsWithMissingFile() throws Exception {
            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingClientsWithWrongExtensionFile() throws Exception {
            // GIVEN
            var file = readFile(ClientControllerTest.class, TEST_FILE_NAME_WRONG_EXTENSION);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME_WRONG_EXTENSION, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingClientsWithWrongContentTypeFile() throws Exception {
            // GIVEN
            var file = readFile(ClientControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_NAME_WRONG_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }
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
    }

    @Nested
    final class TopClientsFetchingTest {
        private static final String ENDPOINT_URL = "/client/fetchTopClients";

        @Test
        void shouldReturnTopClients_whenFetchingWithProperAmountAndSinceDate() throws Exception {
            // GIVEN
            var amount = 3;
            var since = LocalDate.MIN;
            var topClients = List.of(
                    new ClientEntity("test_identifier1", "test_first_name1", "test_last_name1", "test.email1@mail.com", "123456789", MALE, false),
                    new ClientEntity("test_identifier2", "test_first_name2", "test_last_name2", "test.email2@mail.com", "223456789", FEMALE, false),
                    new ClientEntity("test_identifier3", "test_first_name3", "test_last_name3", "test.email3@mail.com", "323456789", MALE, false)
            );
            given(service.fetchTopClients(amount, since)).willReturn(topClients);

            // WHEN
            var result = mockMvc.perform(
                    get("%s?amount=%s&since=%s".formatted(ENDPOINT_URL, amount, since.format(ISO_LOCAL_DATE)))
            );

            // THEN
            result.andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(content().json(MULTIPLE_VALID_CLIENTS, true));
        }

        @Test
        void shouldReturnEmptyList_whenFetchingWithProperAmountAndSinceDateButNoAvailableClients() throws Exception {
            // GIVEN
            var amount = 3;
            var since = LocalDate.MIN;
            given(service.fetchTopClients(amount, since)).willReturn(List.of());

            // WHEN
            var result = mockMvc.perform(
                    get("%s?amount=%s&since=%s".formatted(ENDPOINT_URL, amount, since.format(ISO_LOCAL_DATE)))
            );

            // THEN
            result.andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(content().json("[]", true));
        }

        @Test
        void shouldReturn400_whenFetchingWithMissingAmount() throws Exception {
            // GIVEN
            var since = LocalDate.MIN;

            // WHEN
            var result = mockMvc.perform(
                    get("%s?since=%s".formatted(ENDPOINT_URL, since.format(ISO_LOCAL_DATE)))
            );

            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenFetchingWithMissingSinceDate() throws Exception {
            // GIVEN
            var amount = 3;

            // WHEN
            var result = mockMvc.perform(
                    get("%s?amount=%s".formatted(ENDPOINT_URL, amount))
            );

            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenFetchingWithInvalidAmount() throws Exception {
            // GIVEN
            var amount = "something";

            // WHEN
            var result = mockMvc.perform(
                    get("%s?amount=%s".formatted(ENDPOINT_URL, amount))
            );

            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenFetchingWithInvalidSinceDate() throws Exception {
            // GIVEN
            var since = "something";

            // WHEN
            var result = mockMvc.perform(
                    get("%s?since=%s".formatted(ENDPOINT_URL, since))
            );

            // THEN
            result.andExpect(status().isBadRequest());
        }
    }

    @Nested
    final class ClientUpdatingTest {
        private static final String ENDPOINT_URL = "/client/update";

        @Test
        void shouldReturn200_whenUpdatingValidClient() throws Exception {
            // GIVEN
            var identifier = "test_identifier";
            given(service.fetch(identifier)).willReturn(entity());

            // WHEN
            var result = mockMvc.perform(patch(ENDPOINT_URL)
                    .contentType(APPLICATION_JSON)
                    .content(VALID_CLIENT_NOT_FULL)
            );
            // THEN
            result.andExpect(status().isOk());
        }

        @Test
        void shouldReturn400_whenUpdatingInvalidClientWithoutIdentifier() throws Exception {
            // WHEN
            var result = mockMvc.perform(patch(ENDPOINT_URL)
                    .contentType(APPLICATION_JSON)
                    .content(INVALID_CLIENT_NOT_FULL_MISSING_IDENTIFIER)
            );
            // THEN
            result.andExpect(status().isBadRequest());
        }
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
