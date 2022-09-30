package com.jakubeeee.client;

final class ClientControllerTestConstants {

    static final String VALID_CLIENT = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_IDENTIFIER = """
            {
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_BLANK_IDENTIFIER = """
            {
                "identifier": "",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_FIRST_NAME = """
            {
                "identifier": "test_identifier",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_BLANK_FIRST_NAME = """
            {
                "identifier": "test_identifier",
                "firstName": "",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_LAST_NAME = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_BLANK_LAST_NAME = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_EMAIL = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_BLANK_EMAIL = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_NOT_WELL_FORMED_EMAIL = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_PHONE = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_BLANK_PHONE = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "",
                "gender": "MALE",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_GENDER = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_UNSUPPORTED_GENDER = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "Other",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_MISSING_BANNED = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
            }
            """;

    static final String INVALID_CLIENT_UNSUPPORTED_BANNED = """
            {
                "identifier": "test_identifier",
                "firstName": "test_first_name",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "phone": "123456789",
                "gender": "MALE",
                "banned": other
            }
            """;
}