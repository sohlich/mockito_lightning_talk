package cz.edhouse.workshop.mockito.service.impl;

import cz.edhouse.workshop.mockito.entity.User;
import cz.edhouse.workshop.mockito.repository.UserRepository;
import cz.edhouse.workshop.mockito.service.ActiveDirectoryService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by radek on 4/29/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BussinessServiceImplTest {

    ActiveDirectoryService activeDirectoryService;
    UserRepository userRepository;

    public BussinessServiceImplTest() {
        activeDirectoryService = Mockito.mock(ActiveDirectoryService.class);
        userRepository = Mockito.mock(UserRepository.class);
    }

    /**
     * Shows how to provide mock with multiple return walues
     */
    @Test
    public void testSequence() {

    }


    // Very often test using just
    // simple mock objects.
    @Test
    public void testGetBussinessUnitEmails() throws Exception {

        BussinessServiceImpl service = new BussinessServiceImpl();
        service.setActiveDirecotryService(activeDirectoryService);
        service.setUserRepository(userRepository);

        Set<User> users = new HashSet<>();
        users.add(new User("fg8cvg", "Radek", "Sohlich", "sohlich@email.cz"));
        users.add(new User("fg8cvi", "Peter", "Pan", "pan@email.cz"));

        Mockito.when(userRepository.findAllByBusinessUnit(Matchers.anyString())).thenReturn(users);

        Mockito.when(activeDirectoryService.isUserActive("fg8cvg")).thenReturn(Boolean.TRUE);
        Mockito.when(activeDirectoryService.isUserActive("fg8cvi")).thenReturn(Boolean.FALSE);


        List<String> emails = service.getBussinessUnitEmails("AA");


        Assert.assertEquals(1, emails.size());

    }


    // The mocking object simulates the exception.
    // To verify the behavior of service when the
    // exception occurs in underlying object/service.
    @Test
    public void testNoBusinessUnitFound() {

        final String notSupportedBU = "QQ";
        Mockito.doThrow(new IllegalArgumentException("No such business unit"))
                .when(userRepository).findAllByBusinessUnit(notSupportedBU);

        BussinessServiceImpl service = new BussinessServiceImpl();
        service.setActiveDirecotryService(activeDirectoryService);
        service.setUserRepository(userRepository);
        service.getBussinessUnitEmails(notSupportedBU);
    }

    // This test represents the verification
    // of mock invocation.
    @Test
    public void testInteractionWithAD() {
        BussinessServiceImpl service = new BussinessServiceImpl();
        service.setActiveDirecotryService(activeDirectoryService);
        service.setUserRepository(userRepository);


        Set<User> users = new HashSet<>();
        users.add(new User("fg8cvg", "Radek", "Sohlich", "sohlich@email.cz"));
        users.add(new User("fg8cvi", "Peter", "Pan", "pan@email.cz"));

        Mockito.when(userRepository.findAllByBusinessUnit(Matchers.anyString())).thenReturn(users);

        Mockito.when(activeDirectoryService.isUserActive("fg8cvg")).thenReturn(Boolean.TRUE);
        Mockito.when(activeDirectoryService.isUserActive("fg8cvi")).thenReturn(Boolean.FALSE);


        List<String> emails = service.getBussinessUnitEmails("AA");


        // Called exactly 2 times.
        Mockito.verify(activeDirectoryService, Mockito.times(2)).isUserActive(Matchers.anyString());

    }


    public void testObjectWithMockedMethod() {


    }

}
