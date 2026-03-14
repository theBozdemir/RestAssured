package NewProjects.Serialization_and_Deserialization.Deserialization;

public class GetCourse {
    private String services;
    private String expertise;
    private Courses courses;
    private String instructor;
    private String linkedIn;
    private String url;

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public void setServices(String services) {
        this.services = services;
    }
    public String getUrl() {
        return url;
    }

    public String getServices() {
        return services;
    }

    public String getExpertise() {
        return expertise;
    }

    public Courses getCourses() {
        return courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }


}
