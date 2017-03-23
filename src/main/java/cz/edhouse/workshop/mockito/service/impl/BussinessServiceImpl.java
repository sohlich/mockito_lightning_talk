package cz.edhouse.workshop.mockito.service.impl;

import cz.edhouse.workshop.mockito.dto.ADUserInfo;
import cz.edhouse.workshop.mockito.dto.NotificationDTO;
import cz.edhouse.workshop.mockito.entity.User;
import cz.edhouse.workshop.mockito.exception.ADUserNotFoundEception;
import cz.edhouse.workshop.mockito.repository.UserRepository;
import cz.edhouse.workshop.mockito.service.ActiveDirectoryService;
import cz.edhouse.workshop.mockito.service.BussinessService;
import cz.edhouse.workshop.mockito.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by radek on 4/29/16.
 */
public class BussinessServiceImpl implements BussinessService {


    private UserRepository userRepository;
    private ActiveDirectoryService activeDirecotryService;
    private NotificationService notificationService;


    public List<String> getBussinessUnitEmails(String businessUnit) {
        Set<User> buUsers = userRepository.findAllByBusinessUnit(businessUnit);
        List<String> emails = buUsers
                .stream()
                .filter(p -> {
                    return activeDirecotryService.isUserActive(p.getId());
                })
                .map(User::getEmail)
                .collect(Collectors.toList());
        return emails;
    }


    public void notifyUser(String ffName, String message) throws ADUserNotFoundEception {

        ADUserInfo info = activeDirecotryService.getUserDetail(ffName);
        notificationService.sendNotification(new NotificationDTO());
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setActiveDirecotryService(ActiveDirectoryService activeDirecotryService) {
        this.activeDirecotryService = activeDirecotryService;
    }


    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
