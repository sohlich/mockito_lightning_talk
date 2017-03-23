package cz.edhouse.workshop.mockito.service.impl;

import cz.edhouse.workshop.mockito.dto.ADUserInfo;
import cz.edhouse.workshop.mockito.exception.ADUserNotFoundEception;
import cz.edhouse.workshop.mockito.service.ActiveDirectoryService;
import cz.edhouse.workshop.mockito.service.NotificationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Radomir Sohlich on 18/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleMockitoTest {

    @Mock
    private ActiveDirectoryService adService;


    @Test
    public void testSimpleMock() throws ADUserNotFoundEception {


        // The mock creation could be done via annotation or
        // Mockito.mock
        NotificationService notificationService = Mockito.mock
                (NotificationService.class);

        //The mock setup
        Mockito.when(adService.getUserDetail("fg8cvg")).thenReturn(new
                ADUserInfo("fg8cvg", "Pepa", "Novak", "Edhouse",
                "novak@example.com"));
        Mockito.when(adService.getUserDetail("fg9cvg")).thenThrow(new
                ADUserNotFoundEception());

        BussinessServiceImpl impl = new BussinessServiceImpl();
        impl.setActiveDirecotryService(adService);
        impl.setNotificationService(notificationService);


        impl.notifyUser("fg8cvg", "This is new notification");
        Mockito.verify(notificationService).sendNotification(Matchers.any());


        try {
            impl.notifyUser("fg9cvg", "This is new notification");
            Assert.fail("Should throw exception.");
        } catch (ADUserNotFoundEception e) {
            System.out.println("Cannot find user and this is ok");
        }

    }

}
