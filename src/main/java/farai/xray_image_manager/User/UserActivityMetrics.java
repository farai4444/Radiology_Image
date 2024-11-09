package farai.xray_image_manager.User;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "User_Activity_Metrics")
public class UserActivityMetrics implements Serializable {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = IDENTITY)
    private int userId;
    @Column(name = "LAST_ACTIVE_TIME")
    private LocalDateTime LAST_ACTIVE_TIME;
    @Column(name = "ACCESS_PRIVILEGES")
    private String ACCESS_PRIVILEGES;
    @Column(name = "ACTIVITY_STATUS")
    private String ACTIVITY_STATUS;

    @Override
    public String toString() {
        return "UserActivityMetrics{" +
                "userId=" + userId +
                ", LAST_ACTIVE_TIME=" + LAST_ACTIVE_TIME +
                ", ACCESS_PRIVILEGES='" + ACCESS_PRIVILEGES + '\'' +
                ", ACTIVITY_STATUS='" + ACTIVITY_STATUS + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getLAST_ACTIVE_TIME() {
        return LAST_ACTIVE_TIME;
    }

    public void setLAST_ACTIVE_TIME(LocalDateTime LAST_ACTIVE_TIME) {
        this.LAST_ACTIVE_TIME = LAST_ACTIVE_TIME;
    }

    public String getACCESS_PRIVILEGES() {
        return ACCESS_PRIVILEGES;
    }

    public void setACCESS_PRIVILEGES(String ACCESS_PRIVILEGES) {
        this.ACCESS_PRIVILEGES = ACCESS_PRIVILEGES;
    }

    public String getACTIVITY_STATUS() {
        return ACTIVITY_STATUS;
    }

    public void setACTIVITY_STATUS(String ACTIVITY_STATUS) {
        this.ACTIVITY_STATUS = ACTIVITY_STATUS;
    }
}
