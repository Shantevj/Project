package com.shantev.useful;

public enum Role {
    ADMIN(1), MANAGER(2), SPEAKER(3), USER(4), BANNED(5);
    private int roleId;
    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public static Role getRole(int i) {
        Role [] roles = Role.values();
        for(Role role : roles) {
            if(i == role.roleId) return role;
        }
        return USER;
    }
}
