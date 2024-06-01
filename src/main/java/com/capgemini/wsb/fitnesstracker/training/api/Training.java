package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Represents a training session entity that is stored in the database.
 * This class includes detailed information about the training session such as user, times, activity type, etc.
 *
 * @see User
 * @see ActivityType
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Sets the user associated with this training session.
     *
     * @param user The user who performed the training.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the start time of the training session.
     *
     * @param startTime The time when the training began.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the training session.
     *
     * @param endTime The time when the training ended.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the type of activity for this training session.
     *
     * @param activityType The activity type performed during the training session.
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Sets the distance covered during the training session.
     *
     * @param distance The distance in kilometers covered during the training.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Sets the average speed achieved during the training session.
     *
     * @param averageSpeed The average speed in km/h during the training.
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}