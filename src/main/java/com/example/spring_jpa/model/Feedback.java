package com.example.spring_jpa.model;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "feedback_Id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id" ,
            referencedColumnName = "mobileNumber")
    private User user ;

    @Column
    private String feedback ;

    public Feedback(){
    }
    public Feedback(int id , String feedback , User user){
        this.feedback = feedback;
        this.id  = id;
        this.user = user;
    }

    public String getFeedback(){
        return this.feedback;
    }
    public int getId(){
        return this.id;
    }
    public User getUser(){
        return this.user;
    }

    public void setId( int id ){
        this.id = id;
    }
    public void setFeedback(String FeedBack ){
        this.feedback = feedback;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String  toString(){

        return "FeedBack =[" +
                "id =" + this.id +
                "feedback = " + this.feedback +
                "User id  =" + user.getId() +
                "]";

    }
}
