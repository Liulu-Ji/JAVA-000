package homework.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KlassConfig {
    @Bean
    public Klass getKlass(){
        Klass klass = new Klass();
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student();
        s1.setId(22);
        s1.setName("Ting");
        Student s2 = new Student();
        s1.setId(40);
        s1.setName("Hao");
        students.add(s1);
        students.add(s2);
        klass.setStudents(students);
        return klass;
    }
}
