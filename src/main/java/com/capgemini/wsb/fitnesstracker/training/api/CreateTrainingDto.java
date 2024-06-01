package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * Represents a data transfer object for creating a new training session or updating an existing one.
 * This record captures all necessary details required to register a new training in the system.
 *
 * @param id Optional ID of the training, typically null when creating a new entry.
 * @param userId Identifier of the user who performed the training.
 * @param startTime The start time of the training session.
 * @param endTime The end time of the training session.
 * @param activityType The type of activity performed during the training.
 * @param distance The distance covered during the training, measured in kilometers.
 * @param averageSpeed The average speed maintained during the training, measured in km/h.
 */
public record CreateTrainingDto(
        @Nullable Long id,
        Long userId,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed) {

}
