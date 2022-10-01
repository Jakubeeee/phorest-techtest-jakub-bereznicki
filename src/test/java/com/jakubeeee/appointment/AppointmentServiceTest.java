package com.jakubeeee.appointment;

import com.jakubeeee.client.ClientEntity;
import com.jakubeeee.client.ClientService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.jakubeeee.client.Gender.FEMALE;
import static com.jakubeeee.tools.FileTools.readFile;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
final class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private AppointmentService service;


    @Nested
    final class AppointmentBulkSavingTest {
        private static final String TEST_FILE_NAME = "valid_bulk_save_appointments.csv";
        private static final String TEST_FILE_NAME_MISSING_IDENTIFIER = "invalid_bulk_save_appointments_missing_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_CLIENT_IDENTIFIER = "invalid_bulk_save_appointments_missing_client_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_START_TIME = "invalid_bulk_save_appointments_missing_start_time.csv";
        private static final String TEST_FILE_NAME_MISSING_END_TIME = "invalid_bulk_save_appointments_missing_end_time.csv";

        @Test
        void shouldNotThrow_whenBulkSavingAppointmentsFromValidFile() {
            // GIVEN
            given(clientService.fetch(appointmentIdentifiers())).willReturn(clients(10));

            var file = readFile(AppointmentControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertDoesNotThrow(() -> service.bulkSave(file));
        }

        @Test
        void shouldThrow_whenBulkSavingAppointmentsReferencingNonExistingClients() {
            // GIVEN
            given(clientService.fetch(appointmentIdentifiers())).willReturn(clients(5));

            var file = readFile(AppointmentControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertThrows(IllegalStateException.class, () -> service.bulkSave(file));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                TEST_FILE_NAME_MISSING_IDENTIFIER,
                TEST_FILE_NAME_MISSING_CLIENT_IDENTIFIER,
                TEST_FILE_NAME_MISSING_START_TIME,
                TEST_FILE_NAME_MISSING_END_TIME,
        })
        void shouldThrow_whenBulkSavingAppointmentsFromInvalidFile(String fileName) {
            // GIVEN
            var file = readFile(AppointmentControllerTest.class, fileName);

            // WHEN THEN
            assertThrows(RuntimeException.class, () -> service.bulkSave(file));
        }

        private static List<String> appointmentIdentifiers() {
            return rangeClosed(11, 20)
                    .mapToObj(i -> "test_identifier" + i)
                    .toList();
        }

        private static List<ClientEntity> clients(int amount) {
            return range(11, 11 + amount)
                    .mapToObj(i -> new ClientEntity("test_identifier" + i, "first_name" + i, "last_name" + i, "email" + i, "phone" + i, FEMALE, false))
                    .toList();
        }

    }

}
