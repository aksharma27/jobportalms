package com.jobportal.jobms.job.external;

//add lombok annotations :
//1st 2 automatically generates a default constructor and another constructor containing all the attributes, respectively.
//3rd : @Dataannotation, generates getters, setters, toString(), and other essential methods.
//@NoArgsConstructor
//@AllArgsConstructor
//@Data

public class Company {

    private Long id;
    private String name;
    private String description;


    //REMOVE THIS FOR MICROSERVICE :
    //every company has a list of job : 1 -> many
//    @JsonIgnore     //comp is mapped to job, job is mapped to comp, and so on, to remove this recursive call, use this ANNOTATION
//    @OneToMany(mappedBy = "company")        //company obj in Job -> foreign key will be added in job(compId taken as a foreing key frm comp table)
//    private List<Job> jobs;
//
//    @OneToMany(mappedBy = "company")
//     private List<Review> reviews;

//    public List<Review> getReviews() {
//        return reviews;
//    }

//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//    MONOLITH :
//    public List<Job> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(List<Job> jobs) {
//        this.jobs = jobs;
//    }
}
