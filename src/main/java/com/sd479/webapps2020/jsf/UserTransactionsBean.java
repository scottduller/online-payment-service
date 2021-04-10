/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.UserTransaction;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userTransactionsBean")
@RequestScoped
public class UserTransactionsBean {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    public UserTransactionsBean() {
    }

    public List<UserTransaction> getTransactions() {
        SystemUser currentUser = getLoggedInUser();
        List<UserTransaction> transactions = userTransactionDao.findUserTransactionsByUsername(currentUser.getUsername());

        return transactions;
    }

    public SystemUser getLoggedInUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRemoteUser();

        String currentUserUsername = context.getExternalContext().getRemoteUser();

        SystemUser currentUser = systemUserDao.findSystemUserByUsername(currentUserUsername);

        return currentUser;
    }

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    public UserTransactionDao getUserTransactionDao() {
        return userTransactionDao;
    }

    public void setUserTransactionDao(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.systemUserDao);
        hash = 79 * hash + Objects.hashCode(this.userTransactionDao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserTransactionsBean other = (UserTransactionsBean) obj;
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        return true;
    }

}
