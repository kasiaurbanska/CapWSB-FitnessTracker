package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enumeration of possible types of physical activities.
 * Each type of activity is represented by a specific name useful for UI display purposes.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
