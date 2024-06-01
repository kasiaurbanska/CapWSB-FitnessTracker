package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Component responsible for mapping between {@link Training} entities and {@link TrainingDto} data transfer objects.
 * This class provides functionality to convert data to and from the entity and DTO forms, facilitating the
 * transfer of data across different layers of the application, especially between the service layer and the database.
 *
 * This mapper is essential for encapsulating the logic needed to adapt the database model to the model used in business logic
 * and API responses.
 */
@Component
@Slf4j
class TrainingMapper {

    @Autowired
    private UserRepository userRepository;

    /**
     * Converts a {@link Training} entity into a {@link TrainingDto} object.
     * This method transfers all relevant details from the entity to the DTO form used in higher layers.
     *
     * @param training The training entity to be converted.
     * @return The corresponding TrainingDto with values filled from the training entity.
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    /**
     * Converts a {@link TrainingDto} back to a {@link Training} entity.
     * This method is used when an existing DTO needs to be persisted or updated in the database as an entity.
     *
     * @param trainingDto The DTO that contains training data to be transformed into an entity.
     * @return A Training entity filled with details from the provided DTO.
     */
    Training toEntity(TrainingDto trainingDto) {
        return new Training(
                trainingDto.user(),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }

    /**
     * Converts a {@link CreateTrainingDto} into a {@link Training} entity.
     * This method is specifically used for creating new training entities from the DTOs submitted by clients.
     * It also handles the lookup of associated {@link User} entities based on the user ID provided in the DTO.
     *
     * @param trainingDto The CreateTrainingDto containing initial data for the new training session.
     * @return A new Training entity, ready to be persisted, containing data from the provided DTO.
     * If the user is not found by user ID, it may return a Training entity without a set user.
     */
    Training toEntity(CreateTrainingDto trainingDto) {
        Optional<User> user = userRepository.findById(trainingDto.userId());
        return user.map(value -> new Training(
                value,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed())).orElseGet(() -> new Training(
                null,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()));
    }
}
