package com.jakubeeee.client;

import com.jakubeeee.appointment.AppointmentEntity;
import com.jakubeeee.product.PurchaseEntity;
import com.jakubeeee.product.ServiceEntity;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static com.jakubeeee.client.Gender.FEMALE;
import static com.jakubeeee.client.Gender.MALE;
import static java.math.BigDecimal.*;
import static java.time.Instant.parse;

final class ClientRepositoryTestDataLoader {

    static void load(TestEntityManager entityManager) {
        entityManager.clear();

        var client1 = new ClientEntity("test_identifier1", "first_name1", "last_name1", "email1@mail.com", "123456789", MALE, false);
        var client2 = new ClientEntity("test_identifier2", "first_name2", "last_name2", "email2@mail.com", "223456789", FEMALE, false);
        var client3 = new ClientEntity("test_identifier3", "first_name3", "last_name3", "email3@mail.com", "323456789", MALE, false);
        var client4 = new ClientEntity("test_identifier4", "first_name4", "last_name4", "email4@mail.com", "423456789", FEMALE, false);
        var client5 = new ClientEntity("test_identifier5", "first_name5", "last_name5", "email5@mail.com", "523456789", MALE, false);
        var client6 = new ClientEntity("test_identifier6", "first_name6", "last_name6", "email6@mail.com", "623456789", FEMALE, false);
        var client7 = new ClientEntity("test_identifier7", "first_name7", "last_name7", "email7@mail.com", "723456789", MALE, false);
        var client8 = new ClientEntity("test_identifier8", "first_name8", "last_name8", "email8@mail.com", "823456789", FEMALE, false);
        var client9 = new ClientEntity("test_identifier9", "first_name9", "last_name9", "email9@mail.com", "923456789", MALE, true);
        var client10 = new ClientEntity("test_identifier10", "first_name10", "last_name10", "email10@mail.com", "1023456789", FEMALE, true);
        persistAll(entityManager, client1, client2, client3, client4, client5, client6, client7, client8, client9, client10);

        var appointment1 = new AppointmentEntity("test_identifier101", client1, parse("2015-01-01T00:00:00Z"), parse("2015-01-01T01:00:00Z"));
        var appointment2 = new AppointmentEntity("test_identifier102", client1, parse("2018-01-01T00:00:00Z"), parse("2018-01-01T01:00:00Z"));
        var appointment3 = new AppointmentEntity("test_identifier103", client2, parse("2014-01-01T00:00:00Z"), parse("2014-01-01T01:00:00Z"));
        var appointment4 = new AppointmentEntity("test_identifier104", client2, parse("2019-01-01T00:00:00Z"), parse("2019-01-01T01:00:00Z"));
        var appointment5 = new AppointmentEntity("test_identifier105", client2, parse("2016-01-01T00:00:00Z"), parse("2016-01-01T01:00:00Z"));
        var appointment6 = new AppointmentEntity("test_identifier106", client3, parse("2015-01-01T00:00:00Z"), parse("2015-01-01T01:00:00Z"));
        var appointment7 = new AppointmentEntity("test_identifier107", client5, parse("2017-01-01T00:00:00Z"), parse("2017-01-01T01:00:00Z"));
        var appointment8 = new AppointmentEntity("test_identifier108", client5, parse("2018-01-01T00:00:00Z"), parse("2018-01-01T01:00:00Z"));
        var appointment9 = new AppointmentEntity("test_identifier109", client5, parse("2014-01-01T00:00:00Z"), parse("2014-01-01T01:00:00Z"));
        var appointment10 = new AppointmentEntity("test_identifier110", client5, parse("2020-01-01T00:00:00Z"), parse("2020-01-01T01:00:00Z"));
        var appointment11 = new AppointmentEntity("test_identifier111", client6, parse("2017-01-01T00:00:00Z"), parse("2017-01-01T01:00:00Z"));
        var appointment12 = new AppointmentEntity("test_identifier112", client6, parse("2018-01-01T00:00:00Z"), parse("2018-01-01T01:00:00Z"));
        var appointment13 = new AppointmentEntity("test_identifier113", client7, parse("2015-01-01T00:00:00Z"), parse("2015-01-01T01:00:00Z"));
        var appointment14 = new AppointmentEntity("test_identifier114", client7, parse("2015-01-01T00:00:00Z"), parse("2015-01-01T01:00:00Z"));
        var appointment15 = new AppointmentEntity("test_identifier115", client7, parse("2014-01-01T00:00:00Z"), parse("2014-01-01T01:00:00Z"));
        var appointment16 = new AppointmentEntity("test_identifier116", client8, parse("2018-01-01T00:00:00Z"), parse("2018-01-01T01:00:00Z"));
        var appointment17 = new AppointmentEntity("test_identifier117", client9, parse("2016-01-01T00:00:00Z"), parse("2016-01-01T01:00:00Z"));
        var appointment18 = new AppointmentEntity("test_identifier118", client10, parse("2019-01-01T00:00:00Z"), parse("2019-01-01T01:00:00Z"));
        var appointment19 = new AppointmentEntity("test_identifier119", client10, parse("2020-01-01T00:00:00Z"), parse("2020-01-01T01:00:00Z"));
        var appointment20 = new AppointmentEntity("test_identifier120", client10, parse("2027-01-01T00:00:00Z"), parse("2017-01-01T01:00:00Z"));
        persistAll(entityManager, appointment1, appointment2, appointment3, appointment4, appointment5, appointment6, appointment7, appointment8, appointment9, appointment10,
                appointment11, appointment12, appointment13, appointment14, appointment15, appointment16, appointment17, appointment18, appointment19, appointment20);

        var service1 = new ServiceEntity("test_identifier1001", appointment1, "test_name1", TEN, 10L);
        var service2 = new ServiceEntity("test_identifier1002", appointment1, "test_name2", TWO, 50L);
        var service3 = new ServiceEntity("test_identifier1003", appointment2, "test_name3", ONE, 15L);
        var service4 = new ServiceEntity("test_identifier1004", appointment2, "test_name4", TEN, 30L);
        var service5 = new ServiceEntity("test_identifier1005", appointment3, "test_name5", TWO, 60L);
        var service6 = new ServiceEntity("test_identifier1006", appointment3, "test_name6", TEN, 10L);
        var service7 = new ServiceEntity("test_identifier1007", appointment4, "test_name7", TWO, 50L);
        var service8 = new ServiceEntity("test_identifier1008", appointment4, "test_name8", TWO, 10L);
        var service9 = new ServiceEntity("test_identifier1009", appointment4, "test_name9", TEN, 20L);
        var service10 = new ServiceEntity("test_identifier1010", appointment5, "test_name10", ONE, 25L);
        var service11 = new ServiceEntity("test_identifier1011", appointment6, "test_name11", TWO, 35L);
        var service12 = new ServiceEntity("test_identifier1012", appointment7, "test_name12", ONE, 40L);
        var service13 = new ServiceEntity("test_identifier1013", appointment8, "test_name13", TEN, 10L);
        var service14 = new ServiceEntity("test_identifier1014", appointment10, "test_name14", TWO, 20L);
        var service15 = new ServiceEntity("test_identifier1015", appointment11, "test_name15", ONE, 40L);
        var service16 = new ServiceEntity("test_identifier1016", appointment12, "test_name16", TWO, 25L);
        var service17 = new ServiceEntity("test_identifier1017", appointment12, "test_name17", ONE, 60L);
        var service18 = new ServiceEntity("test_identifier1018", appointment15, "test_name18", TEN, 35L);
        var service19 = new ServiceEntity("test_identifier1019", appointment18, "test_name19", ONE, 85L);
        var service20 = new ServiceEntity("test_identifier1020", appointment20, "test_name20", TEN, 5L);
        persistAll(entityManager, service1, service2, service3, service4, service5, service6, service7, service8, service9, service10,
                service11, service12, service13, service14, service15, service16, service17, service18, service19, service20);

        var purchase1 = new PurchaseEntity("test_identifier1101", appointment1, "test_name101", TWO, 50L);
        var purchase2 = new PurchaseEntity("test_identifier1102", appointment3, "test_name102", TWO, 20L);
        var purchase3 = new PurchaseEntity("test_identifier1103", appointment5, "test_name103", ONE, 15L);
        var purchase4 = new PurchaseEntity("test_identifier1104", appointment6, "test_name104", TWO, 5L);
        var purchase5 = new PurchaseEntity("test_identifier1105", appointment6, "test_name105", TEN, 10L);
        var purchase6 = new PurchaseEntity("test_identifier1106", appointment7, "test_name106", ONE, 30L);
        var purchase7 = new PurchaseEntity("test_identifier1107", appointment9, "test_name107", TWO, 35L);
        var purchase8 = new PurchaseEntity("test_identifier1108", appointment10, "test_name108", TEN, 25L);
        var purchase9 = new PurchaseEntity("test_identifier1109", appointment11, "test_name109", TWO, 20L);
        var purchase10 = new PurchaseEntity("test_identifier1110", appointment11, "test_name110", ONE, 40L);
        var purchase11 = new PurchaseEntity("test_identifier1111", appointment11, "test_name111", ONE, 15L);
        var purchase12 = new PurchaseEntity("test_identifier1112", appointment12, "test_name112", TEN, 20L);
        var purchase13 = new PurchaseEntity("test_identifier1113", appointment12, "test_name113", TWO, 10L);
        var purchase14 = new PurchaseEntity("test_identifier1114", appointment13, "test_name114", TEN, 35L);
        var purchase15 = new PurchaseEntity("test_identifier1115", appointment14, "test_name115", TWO, 30L);
        var purchase16 = new PurchaseEntity("test_identifier1116", appointment15, "test_name116", TEN, 65L);
        var purchase17 = new PurchaseEntity("test_identifier1117", appointment16, "test_name117", TWO, 110L);
        var purchase18 = new PurchaseEntity("test_identifier1118", appointment19, "test_name118", TWO, 30L);
        var purchase19 = new PurchaseEntity("test_identifier1119", appointment19, "test_name119", ONE, 20L);
        var purchase20 = new PurchaseEntity("test_identifier1120", appointment20, "test_name120", TWO, 45L);
        persistAll(entityManager, purchase1, purchase2, purchase3, purchase4, purchase5, purchase6, purchase7, purchase8, purchase9, purchase10,
                purchase11, purchase12, purchase13, purchase14, purchase15, purchase16, purchase17, purchase18, purchase19, purchase20);
    }

    private static void persistAll(TestEntityManager entityManager, Object... entities) {
        Arrays.stream(entities).forEach(entityManager::persist);

    }

}
