package Day2;

public class Pojo_PostRequest {

    String name;
    String location;
    String phone;
    String course[];
    String id;

    public void setID(String id){
        this.id=id;
    }
    public String getID(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location=location;
    }
    public String getLocation(){
        return location;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }
    public void setCourse(String course[]){
        this.course=course;
    }
    public String [] getCourse(){
        return course;
    }
}
