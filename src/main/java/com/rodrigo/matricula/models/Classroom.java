package com.rodrigo.matricula.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "room")
    private String room;

    @Column(name = "ids_students")
    private String idsStudents;

    @Column(name = "id_teacher")
    private Long idTeacher;

    public Classroom(String name, String room, String idsStudents) {
        this.name = name;
        this.room = room;
        this.idsStudents = idsStudents;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getIdsStudents() {
        return idsStudents;
    }

    public void setIdsStudents(String idsStudentsJson) {
        this.idsStudents = idsStudentsJson;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    
}
