package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import com.jakubeeee.appointment.AppointmentService;
import com.jakubeeee.client.ClientEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static com.jakubeeee.client.Gender.MALE;
import static com.jakubeeee.tools.FileTools.readFile;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
final class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private ProductService service;

    @Nested
    final class ServiceBulkSavingTest {
        private static final String TEST_FILE_NAME = "valid_bulk_save_services.csv";
        private static final String TEST_FILE_NAME_MISSING_IDENTIFIER = "invalid_bulk_save_services_missing_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_APPOINTMENT_IDENTIFIER = "invalid_bulk_save_services_missing_appointment_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_NAME = "invalid_bulk_save_services_missing_name.csv";
        private static final String TEST_FILE_NAME_MISSING_PRICE = "invalid_bulk_save_services_missing_price.csv";
        private static final String TEST_FILE_NAME_MISSING_LOYALTY_POINTS = "invalid_bulk_save_services_missing_loyalty_points.csv";

        @Test
        void shouldNotThrow_whenBulkSavingServicesFromValidFile() {
            // GIVEN
            given(appointmentService.fetch(appointmentIdentifiers())).willReturn(appointments(10));

            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertDoesNotThrow(() -> service.bulkSaveServices(file));
        }

        @Test
        void shouldThrow_whenBulkSavingServicesReferencingNonExistingClients() {
            // GIVEN
            given(appointmentService.fetch(appointmentIdentifiers())).willReturn(appointments(5));

            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertThrows(IllegalStateException.class, () -> service.bulkSaveServices(file));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                TEST_FILE_NAME_MISSING_IDENTIFIER,
                TEST_FILE_NAME_MISSING_APPOINTMENT_IDENTIFIER,
                TEST_FILE_NAME_MISSING_NAME,
                TEST_FILE_NAME_MISSING_PRICE,
                TEST_FILE_NAME_MISSING_LOYALTY_POINTS
        })
        void shouldThrow_whenBulkSavingServicesFromInvalidFile(String fileName) {
            // GIVEN
            var file = readFile(ProductControllerTest.class, fileName);

            // WHEN THEN
            assertThrows(RuntimeException.class, () -> service.bulkSaveServices(file));
        }
    }

    @Nested
    final class PurchaseBulkSavingTest {
        private static final String TEST_FILE_NAME = "valid_bulk_save_purchases.csv";
        private static final String TEST_FILE_NAME_MISSING_IDENTIFIER = "invalid_bulk_save_purchases_missing_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_APPOINTMENT_IDENTIFIER = "invalid_bulk_save_purchases_missing_appointment_identifier.csv";
        private static final String TEST_FILE_NAME_MISSING_NAME = "invalid_bulk_save_purchases_missing_name.csv";
        private static final String TEST_FILE_NAME_MISSING_PRICE = "invalid_bulk_save_purchases_missing_price.csv";
        private static final String TEST_FILE_NAME_MISSING_LOYALTY_POINTS = "invalid_bulk_save_purchases_missing_loyalty_points.csv";

        @Test
        void shouldNotThrow_whenBulkSavingPurchasesFromValidFile() {
            // GIVEN
            given(appointmentService.fetch(appointmentIdentifiers())).willReturn(appointments(10));

            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertDoesNotThrow(() -> service.bulkSavePurchases(file));
        }

        @Test
        void shouldThrow_whenBulkSavingPurchasesReferencingNonExistingClients() {
            // GIVEN
            given(appointmentService.fetch(appointmentIdentifiers())).willReturn(appointments(5));

            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);

            // WHEN THEN
            assertThrows(IllegalStateException.class, () -> service.bulkSavePurchases(file));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                TEST_FILE_NAME_MISSING_IDENTIFIER,
                TEST_FILE_NAME_MISSING_APPOINTMENT_IDENTIFIER,
                TEST_FILE_NAME_MISSING_NAME,
                TEST_FILE_NAME_MISSING_PRICE,
                TEST_FILE_NAME_MISSING_LOYALTY_POINTS
        })
        void shouldThrow_whenBulkSavingPurchasesFromInvalidFile(String fileName) {
            // GIVEN
            var file = readFile(ProductControllerTest.class, fileName);

            // WHEN THEN
            assertThrows(RuntimeException.class, () -> service.bulkSavePurchases(file));
        }
    }

    private static List<String> appointmentIdentifiers() {
        return rangeClosed(11, 20)
                .mapToObj(i -> "test_identifier" + i)
                .toList();
    }

    private static List<AppointmentEntity> appointments(int amount) {
        return range(11, 11 + amount)
                .mapToObj(i -> new AppointmentEntity(
                        "test_identifier" + i,
                        new ClientEntity("test_identifier" + 100 + i, "first_name" + 100 + i, "last_name" + 100 + i, "email" + 100 + i, "phone" + 100 + i, MALE, false),
                        Instant.MIN,
                        Instant.MAX))
                .toList();
    }
}
