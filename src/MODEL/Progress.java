package MODEL;

public class Progress {
    private int progressId;
    private int userId;
    private int moduleId;
    private String progressStatus;
    private String completionDate; // Consider using java.time.LocalDate for dates
    private int score;
    private int attempts;

    // Constructors
    public Progress() {}

    public Progress(int userId, int moduleId, String progressStatus, String completionDate, int score, int attempts) {
        this.userId = userId;
        this.moduleId = moduleId;
        this.progressStatus = progressStatus;
        this.completionDate = completionDate;
        this.score = score;
        this.attempts = attempts;
    }

    public Progress(int progressId, int userId, int moduleId, String progressStatus, String completionDate, int score, int attempts) {
        this.progressId = progressId;
        this.userId = userId;
        this.moduleId = moduleId;
        this.progressStatus = progressStatus;
        this.completionDate = completionDate;
        this.score = score;
        this.attempts = attempts;
    }

    // Getters and Setters
    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "progressId=" + progressId +
                ", userId=" + userId +
                ", moduleId=" + moduleId +
                ", progressStatus='" + progressStatus + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", score=" + score +
                ", attempts=" + attempts +
                '}';
    }
}
