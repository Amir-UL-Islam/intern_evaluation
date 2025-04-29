package org.spring.intro.config.context;

import org.spring.intro.model.entity.MUser;

// SecurityContext.java
public class SecurityContext {
    private static final ThreadLocal<MUser> currentUser = new ThreadLocal<>();

    public MUser getCurrentUser() {
        return currentUser.get();
    }

    public void setCurrentUser(MUser user) {
        currentUser.set(user);
    }

    public void clear() {
        currentUser.remove();
    }

    public boolean isAuthenticated() {
        return currentUser.get() != null;
    }
}