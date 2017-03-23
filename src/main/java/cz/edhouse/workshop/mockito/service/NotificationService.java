package cz.edhouse.workshop.mockito.service;

import cz.edhouse.workshop.mockito.dto.NotificationDTO;

/**
 * Created by Radomir Sohlich on 18/03/2017.
 */
public interface NotificationService {

    public void sendNotification(NotificationDTO notification);
}
