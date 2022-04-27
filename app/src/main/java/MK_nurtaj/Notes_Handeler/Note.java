package MK_nurtaj.Notes_Handeler;

public class Note {

    String key = "";
    String topicName = "";
    String course_id = "";
    String datetime = "";
    String description = "";


    public Note(String key, String topicName, String course_id, String datetime, String description) {
        this.key = key;
        this.topicName = topicName;
        this.course_id = course_id;
        this.datetime = datetime;
        this.description = description;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
