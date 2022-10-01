package com.jakubeeee.product;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static com.jakubeeee.tools.FileTools.readFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
final class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Nested
    final class ServiceBulkSavingTest {
        private static final String ENDPOINT_URL = "/product/service/bulkSave";
        private static final String TEST_FILE_NAME = "valid_bulk_save_services.csv";
        private static final String TEST_FILE_NAME_WRONG_EXTENSION = "invalid_bulk_save_services_wrong_extension.notcsv";
        private static final String TEST_FILE_MULTIPART_NAME = "file";
        private static final String TEST_FILE_CONTENT_TYPE = "text/csv";
        private static final String TEST_FILE_NAME_WRONG_CONTENT_TYPE = "text/notcsv";

        @Test
        void shouldReturn200_whenBulkSavingServicesWithValidFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform(multipart(ENDPOINT_URL).file(multipartFile));
            // THEN
            result.andExpect(status().isOk());
        }

        @Test
        void shouldReturn400_whenBulkSavingServicesWithMissingFile() throws Exception {
            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingServicesWithWrongExtensionFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME_WRONG_EXTENSION);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME_WRONG_EXTENSION, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingServicesWithWrongContentTypeFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_NAME_WRONG_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }
    }

    @Nested
    final class PurchaseBulkSavingTest {
        private static final String ENDPOINT_URL = "/product/purchase/bulkSave";
        private static final String TEST_FILE_NAME = "valid_bulk_save_purchases.csv";
        private static final String TEST_FILE_NAME_WRONG_EXTENSION = "invalid_bulk_save_purchases_wrong_extension.notcsv";
        private static final String TEST_FILE_MULTIPART_NAME = "file";
        private static final String TEST_FILE_CONTENT_TYPE = "text/csv";
        private static final String TEST_FILE_NAME_WRONG_CONTENT_TYPE = "text/notcsv";

        @Test
        void shouldReturn200_whenBulkSavingPurchasesWithValidFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform(multipart(ENDPOINT_URL).file(multipartFile));
            // THEN
            result.andExpect(status().isOk());
        }

        @Test
        void shouldReturn400_whenBulkSavingPurchasesWithMissingFile() throws Exception {
            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingPurchasesWithWrongExtensionFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME_WRONG_EXTENSION);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME_WRONG_EXTENSION, TEST_FILE_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturn400_whenBulkSavingPurchasesWithWrongContentTypeFile() throws Exception {
            // GIVEN
            var file = readFile(ProductControllerTest.class, TEST_FILE_NAME);
            var multipartFile = new MockMultipartFile(TEST_FILE_MULTIPART_NAME, TEST_FILE_NAME, TEST_FILE_NAME_WRONG_CONTENT_TYPE, file);

            // WHEN
            var result = mockMvc.perform((multipart(ENDPOINT_URL).file(multipartFile)));
            // THEN
            result.andExpect(status().isBadRequest());
        }
    }

}
