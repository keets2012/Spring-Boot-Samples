package com.blueskykong.auth.demo.entity;

import java.util.UUID;

/**
 * Created by keets on 2017/11/21.
 */
public class Permission {

    private UUID id;

    private String permission;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
