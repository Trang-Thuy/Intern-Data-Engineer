// import java.util.Objects;
// public class Student {
//     private String name;
//     private Integer age;

//     public Student (String name, Integer age){
//         this.name = name;
//         this.age = age;
//     }

//     //getter and setter method
//     public String getName(){
//         return name;
//     }
//     public void setName (String name){
//         this.name = name;
//     }
//     public Integer getAge(){
//         return age;
//     }
//     public void setAge(Integer age){
//         this.age = age;
//     }

//     @Override
//     public String toString(){
//         return "Student[name="+name+",age="+age+"]";
//     }
//     @Override
//     public int hashCode() {
// 		return Objects.hash(name, age);
// 	}
//     @Override
//     public boolean equals(Object other){
//         if (other == null) return false;
//         if (other == this) return true;
//         if (getClass() != other.getClass())
//             return false;
//         Student obj = (Student) other;
//         return Objects.equals(name, obj.name) && age == obj.age;
// }
// }
