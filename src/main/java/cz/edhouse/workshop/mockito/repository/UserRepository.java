package cz.edhouse.workshop.mockito.repository;

import cz.edhouse.workshop.mockito.entity.User;

import java.util.Set;

/**
 * Repository for users.
 * <p>
 * Created by radek on 4/29/16.
 */
public interface UserRepository {

    /**
     * Finds all users from given business unit.
     *
     * @param bu business unit code.
     * @return set of users from BU or empty list.
     */
    public Set<User> findAllByBusinessUnit(String bu);


}
