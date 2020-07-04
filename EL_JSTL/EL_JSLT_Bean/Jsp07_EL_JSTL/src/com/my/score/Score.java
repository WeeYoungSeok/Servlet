package com.my.score;

public class Score {

   private String name;
   private int kor;
   private int eng;
   private int math;

   
   public Score() {
      
   }
  
   public Score(String name, int kor, int eng, int math) {
      this.name = name;
      this.kor = kor;
      this.eng = eng;
      this.math = math;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getKor() {
      return kor;
   }
   public void setKor(int kor) {
      this.kor = kor;
   }
   public int getEng() {
      return eng;
   }
   public void setEng(int eng) {
      this.eng = eng;
   }
   public int getMath() {
      return math;
   }
   public void setMath(int math) {
      this.math = math;
   }
   
 
   public int getSum() {
      return kor + eng + math;
   }
   
   public double getAvg() {
      return (double) getSum() / 3;
   }
 
   public String getGrade() {
      double score = getAvg();
      if(score >= 90) {
         return "A";
      }else if(score >= 80) {
         return "B";
      }else if(score >= 70) {
         return "C";
      }else if(score >= 60) {
         return "D";
      }else {
         return "F";
      }
      
   }

   
   
   
}