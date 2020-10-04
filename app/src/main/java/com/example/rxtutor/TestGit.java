package com.example.rxtutor;

public class TestGit {

    //123
   private int idName;

    public TestGit(int idName) {
        this.idName = idName;
    }

    public int getIdName() {
        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }
   
   @Override
    public String toString() {
        return "TestGit{" +
                "idName=" + idName +
                ", name='" + name + '\'' +
                '}';
    }

}
