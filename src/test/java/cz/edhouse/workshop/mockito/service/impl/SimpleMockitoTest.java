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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Iterator;

/**
 * Created by Radomir Sohlich on 18/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleMockitoTest {

    @Mock
    private ActiveDirectoryService adService;



    // One call
    @Test
    public void testOne() throws ADUserNotFoundEception {

        Mockito.when(adService.getUserDetail(Matchers.anyString())).thenReturn(new
                ADUserInfo("fg8cvg", "Pepa", "Novak", "Edhouse",
                "novak@example.com"));


       ADUserInfo info =  adService.getUserDetail("fxxxx");
       Assert.assertEquals("Pepa",info.getFirstName());
    }


    // Sequence calls
    @Test
    public void testSequence(){


        Iterator<String> iterator = Mockito.mock(Iterator.class);
        Mockito.when(iterator.next()).thenReturn("One").thenReturn("Two");
        Mockito.when(iterator.hasNext()).thenReturn(true).thenReturn(true)
                .thenReturn(false);

        while (iterator.hasNext()){
            Assert.assertNotNull(iterator.next());
        }

    }



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

        //Set mocks to object
        BussinessServiceImpl impl = new BussinessServiceImpl();
        impl.setActiveDirecotryService(adService);
        impl.setNotificationService(notificationService);



        //Call service
        impl.notifyUser("fg8cvg", "This is new notification");



        // Verifies the object call
        Mockito.verify(notificationService).sendNotification(Matchers.any());


        try {
            //Call service with throw
            impl.notifyUser("fg9cvg", "This is new notification");
            Assert.fail("Should throw exception.");
        } catch (ADUserNotFoundEception e) {
            System.out.println("Cannot find user and this is ok");
        }

    }




    public void moreSofisticatedTest() throws ADUserNotFoundEception {

        NotificationService notificationService = Mockito.mock
                (NotificationService.class);

        //The mock setup
        Mockito.when(adService.getUserDetail("fg8cvg")).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {

                // The way how to verify parameters
                Assert.assertEquals("fg8cvg",invocationOnMock.getArgumentAt
                        (0,String.class));

                return new
                        ADUserInfo("fg8cvg", "Pepa", "Novak", "Edhouse",
                        "novak@example.com");
            }
        });


    }





}
