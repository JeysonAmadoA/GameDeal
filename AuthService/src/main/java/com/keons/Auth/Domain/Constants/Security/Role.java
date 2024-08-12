package com.keons.Auth.Domain.Constants.Security;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {

    ADMIN(
            Arrays.asList(
                    Permission.MONITOR_APP,
                    Permission.CREATE_ADMIN,
                    Permission.CREATE_CUSTOMER,
                    Permission.UPDATE_USERS,
                    Permission.DELETE_USERS,
                    Permission.GET_ALL_USERS,
                    Permission.GET_ONE_USER
            )
    ),

    CUSTOMER(
            List.of(
                    Permission.CREATE_CUSTOMER,
                    Permission.GET_ONE_USER
            )
    );

    private final List<Permission> permissions;
    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }
}