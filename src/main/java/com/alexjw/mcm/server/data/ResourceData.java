package com.alexjw.mcm.server.data;

public class ResourceData {
    public String resourceName, resourceSize;

    public ResourceData(String resourceName, String resourceSize) {
        this.resourceName = resourceName;
        this.resourceSize = resourceSize;
    }

    public String toString() {
        return resourceName + '|' + resourceSize;
    }
}
