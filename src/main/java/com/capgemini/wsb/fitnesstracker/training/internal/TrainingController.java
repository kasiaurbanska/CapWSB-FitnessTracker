package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for handling training-related operations.
 * Provides endpoints for managing training data such as creation, retrieval, update, and deletion.
 *
 * @see TrainingServiceImpl
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    /**
     * Endpoint for creating a new training session. This method takes a CreateTrainingDto object,
     * processes it, and returns the created TrainingDto.
     *
     * @param trainingDto Data transfer object containing all necessary details to create a new training session.
     * @return ResponseEntity containing the created TrainingDto and the HTTP status code.
     */
    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody CreateTrainingDto trainingDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(trainingService.createTraining(trainingDto));
    }


    /**
     * Endpoint to retrieve all training sessions stored in the database.
     *
     * @return ResponseEntity containing a list of all TrainingDto and the HTTP status code.
     */
    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> trainings = trainingService.findAllTrainings();
        return ResponseEntity.ok(trainings);
    }

    /**
     * Endpoint to retrieve all training sessions for a specific user, identified by their user ID.
     *
     * @param userId The ID of the user whose training sessions are to be retrieved.
     * @return ResponseEntity containing a list of TrainingDto and the HTTP status code.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsByUserId(@PathVariable Long userId) {
        List<TrainingDto> trainings = trainingService.findTrainingsByUserId(userId);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Endpoint to retrieve training sessions by activity type, specified as a query parameter.
     *
     * @param activityType The type of activity for which to retrieve training sessions.
     * @return ResponseEntity containing a list of TrainingDto for the specified activity type and the HTTP status code.
     */
    @GetMapping("/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivity(@RequestParam String activityType) {
        List<TrainingDto> trainings = trainingService.findTrainingsByActivity(ActivityType.valueOf(activityType));
        return ResponseEntity.ok(trainings);
    }

    /**
     * Endpoint to retrieve all training sessions that were completed after a specified date.
     *
     * @param afterTime The date after which completed trainings are to be retrieved.
     * @return ResponseEntity containing a list of TrainingDto for trainings completed after the specified date and the HTTP status code.
     */
    @GetMapping("/finished/{afterTime}")
    public ResponseEntity<List<TrainingDto>> getCompletedTrainingsAfter(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        List<TrainingDto> trainings = trainingService.findCompletedTrainingsAfter(afterTime);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Endpoint for updating an existing training session with new data.
     *
     * @param trainingId The ID of the training session to be updated.
     * @param trainingDto Data transfer object containing updated training data.
     * @return ResponseEntity containing the updated TrainingDto if found, or NotFound status if not found.
     */
    @PutMapping("/{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long trainingId, @RequestBody CreateTrainingDto trainingDto) {
        Optional<TrainingDto> updatedTrainingDto = trainingService.updateTraining(trainingId, trainingDto);
        return updatedTrainingDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for deleting a training session by its ID.
     *
     * @param id The ID of the training to be deleted.
     * @return ResponseEntity indicating success (204 No Content) or not found (404 Not Found).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        boolean deleted = trainingService.deleteTraining(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
