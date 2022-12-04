package id.ac.umn.rider;

public class Reminder {
    private String reminderPart;
    private String reminderDate;
    private String reminderOptimalDate;
    private int reminderImage;

    public Reminder(String reminderPart, String reminderDate, String reminderOptimalDate) {
        this.reminderPart = reminderPart;
        this.reminderDate = reminderDate;
        this.reminderOptimalDate = reminderOptimalDate;
    }

    public String getReminderPart() {
        return reminderPart;
    }

    public void setReminderPart(String reminderPart) {
        this.reminderPart = reminderPart;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderOptimalDate() {
        return reminderOptimalDate;
    }

    public void setReminderOptimalDate(String reminderOptimalDate) {
        this.reminderOptimalDate = reminderOptimalDate;
    }

    public int getReminderImage() {
        return reminderImage;
    }

    public void setReminderImage(int reminderImage) {
        this.reminderImage = reminderImage;
    }
}
