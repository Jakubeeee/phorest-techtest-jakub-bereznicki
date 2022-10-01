package com.jakubeeee.client;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jakubeeee.tools.FileTools.readFile;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
final class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService service;


    @Nested
    final class ClientBulkSavingTest {
        private static final String TEST_FILE_NAME = "valid_bulk_save_clients.csv";
        private static final String TEST_FILE_NAME_MISSING_IDENTIFIER = "invalid_bulk_save_clients_missing_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_FIRST_NAME = "invalid_bulk_save_clients_missing_first_name.csv";
        private static final String TEST_FILE_NAME_MISSING_LAST_NAME = "invalid_bulk_save_clients_missing_last_name.csv";
        private static final String TEST_FILE_NAME_MISSING_EMAIL = "invalid_bulk_save_clients_missing_email.csv";
        private static final String TEST_FILE_NAME_MISSING_PHONE = "invalid_bulk_save_clients_missing_phone.csv";
        private static final String TEST_FILE_NAME_MISSING_GENDER = "invalid_bulk_save_clients_missing_gender.csv";
        private static final String TEST_FILE_NAME_MISSING_BANNED = "invalid_bulk_save_clients_missing_banned.csv";

        @Test
        void shouldNotThrow_whenBulkSavingClientsFromValidFile() {
            // GIVEN
            var file = readFile(ClientControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertDoesNotThrow(() -> service.bulkSave(file));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                TEST_FILE_NAME_MISSING_IDENTIFIER,
                TEST_FILE_NAME_MISSING_FIRST_NAME,
                TEST_FILE_NAME_MISSING_LAST_NAME,
                TEST_FILE_NAME_MISSING_EMAIL,
                TEST_FILE_NAME_MISSING_PHONE,
                TEST_FILE_NAME_MISSING_GENDER,
                TEST_FILE_NAME_MISSING_BANNED
        })
        void shouldThrow_whenBulkSavingClientsFromInvalidFile(String fileName) {
            // GIVEN
            var file = readFile(ClientControllerTest.class, fileName);

            // WHEN THEN
            assertThrows(RuntimeException.class, () -> service.bulkSave(file));
        }
    }

}
