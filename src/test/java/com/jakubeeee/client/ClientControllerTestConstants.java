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

    static final String MULTIPLE_VALID_CLIENTS = """
            [
                {
                    "identifier": "test_identifier1",
                    "firstName": "test_first_name1",
                    "lastName": "test_last_name1",
                    "email": "test.email1@mail.com",
                    "phone": "123456789",
                    "gender": "MALE",
                    "banned": false
                },
                {
                    "identifier": "test_identifier2",
                    "firstName": "test_first_name2",
                    "lastName": "test_last_name2",
                    "email": "test.email2@mail.com",
                    "phone": "223456789",
                    "gender": "FEMALE",
                    "banned": false
                },
                {
                    "identifier": "test_identifier3",
                    "firstName": "test_first_name3",
                    "lastName": "test_last_name3",
                    "email": "test.email3@mail.com",
                    "phone": "323456789",
                    "gender": "MALE",
                    "banned": false
                }
            ]
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

    static final String VALID_CLIENT_NOT_FULL = """
            {
                "identifier": "test_identifier",
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "banned": false
            }
            """;

    static final String INVALID_CLIENT_NOT_FULL_MISSING_IDENTIFIER = """
            {
                "lastName": "test_last_name",
                "email": "test.email@mail.com",
                "banned": false
            }
            """;
}