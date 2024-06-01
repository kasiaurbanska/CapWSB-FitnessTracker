package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * Data transfer object that represents the detailed view of a training session.
 * Used to transfer training data across different layers of the application.
 *
 * @param id The database identifier of the training.
 * @param user The user who performed the training.
 * @param startTime Start time of the training session.
 * @param endTime End time of the training session.
 * @param activityType Type of activity performed.
 * @param distance Distance covered in kilometers.
 * @param averageSpeed Average speed during the training in km/h.
 */
public record TrainingDto(
        @Nullable Long id,
        User user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed) {

}
