package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    @Override
    public Optional<TrainingDto> getTraining(final Long trainingId) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        return training.map(trainingMapper::toDto).or(Optional::empty);
    }

    @Override
    public TrainingDto createTraining(CreateTrainingDto newTrainingDto) {
        log.info("Creating Training {}", newTrainingDto);
        log.info("User {}", newTrainingDto.userId());
        if (newTrainingDto.id() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        Training createdTraining = trainingRepository.save(trainingMapper.toEntity(newTrainingDto));

        return trainingMapper.toDto(createdTraining);
    }

    @Override
    public List<TrainingDto> findAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> findTrainingsByUserId(Long userId) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> findTrainingsByActivity(ActivityType activityType) {
        return trainingRepository.findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), activityType))
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> findCompletedTrainingsAfter(Date date) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getEndTime().compareTo(date) > 0)
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public Optional<TrainingDto> updateTraining(Long trainingId, CreateTrainingDto newTrainingDto) {
        Training newTraining = trainingMapper.toEntity(newTrainingDto);
        return trainingRepository.findById(trainingId).map(
                training -> {
                    training.setUser(newTraining.getUser());
                    training.setStartTime(newTraining.getStartTime());
                    training.setEndTime(newTraining.getEndTime());
                    training.setActivityType(newTraining.getActivityType());
                    training.setDistance(newTraining.getDistance());
                    training.setAverageSpeed(newTraining.getAverageSpeed());
                    return trainingMapper.toDto(trainingRepository.save(training));
                });
    }

    @Override
    public boolean deleteTraining(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
