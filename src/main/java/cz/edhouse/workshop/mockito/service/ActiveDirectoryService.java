package cz.edhouse.workshop.mockito.service;

import cz.edhouse.workshop.mockito.dto.ADUserInfo;
import cz.edhouse.workshop.mockito.exception.ADUserNotFoundEception;

/**
 * Created by radek on 4/29/16.
 */
public interface ActiveDirectoryService {

    /**
     * Returns if the user is active in AD.
     *
     * @param userId identfier of user
     * @return true if active, false if not or not present in ADs
     */
    public boolean isUserActive(String userId);


    /**
     * Returns the user information.
     *
     * @return
     */
    public ADUserInfo getUserDetail(String ffName) throws
            ADUserNotFoundEception;


}
