package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining the operations for managing trainings within the application.
 * Provides methods for creating, retrieving, updating, and deleting training sessions.
 */
public interface TrainingService {

    /**
     * Creates a new training session based on the provided data transfer object.
     * This method is responsible for validation and persistence of the training session data.
     *
     * @param newTrainingDto The training data transfer object containing all necessary information to register a new training.
     * @return TrainingDto representing the newly created training session.
     * @throws IllegalArgumentException if the training data includes an ID, indicating it is not new.
     */
    TrainingDto createTraining(CreateTrainingDto newTrainingDto);

    /**
     * Retrieves a list of all training sessions.
     *
     * @return List of TrainingDto representing all the training sessions stored in the database.
     */
    List<TrainingDto> findAllTrainings();

    /**
     * Finds all training sessions associated with a specific user ID.
     *
     * @param userId The ID of the user whose trainings are to be retrieved.
     * @return List of TrainingDto corresponding to the trainings of the specified user.
     */
    List<TrainingDto> findTrainingsByUserId(Long userId);

    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param activityType The type of activity to filter the training sessions.
     * @return List of TrainingDto representing training sessions of the specified activity type.
     */
    List<TrainingDto> findTrainingsByActivity(ActivityType activityType);

    /**
     * Finds all training sessions that were completed after a specified date.
     *
     * @param date The date after which completed trainings are to be retrieved.
     * @return List of TrainingDto for trainings completed after the specified date.
     */
    List<TrainingDto> findCompletedTrainingsAfter(Date date);

    /**
     * Updates an existing training session with the provided new training data.
     *
     * @param trainingId The ID of the training session to be updated.
     * @param newTrainingDto The new training data for updating the existing session.
     * @return Optional containing the updated TrainingDto, or an empty Optional if no training with the given ID exists.
     */
    Optional<TrainingDto> updateTraining(Long trainingId, CreateTrainingDto newTrainingDto);

    /**
     * Deletes a training session by its ID.
     *
     * @param id The ID of the training session to be deleted.
     * @return true if the training was successfully deleted, false otherwise.
     */
    boolean deleteTraining(Long id);
}
