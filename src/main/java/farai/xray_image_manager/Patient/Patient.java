package farai.xray_image_manager.Patient;

import jakarta.persistence.*;

import java.io.Serializable;
import static jakarta.persistence.GenerationType.IDENTITY;


/*@NamedQueries({
        @NamedQuery(name= Patient.FIND_ALL, query="UPDATE Patient SET age = 25, gender = 'm', country = 'Zimbabwe', city = 'Chinhoyi', address = 'Gunhill 4000' WHERE patient_Id = '1' ")})
@SqlResultSetMapping(
        name="patientResult",
        entities=@EntityResult(entityClass = Patient.class)
)*/
@Entity
@Table(name = "patient")
public class Patient implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "patient_Id")
    private String patient_Id;
    @Column(name = "patient_Name")
    private String patient_Name;
    @Column(name = "patient_Surname")
    private String patient_Surname;
    @Column(name = "address")
    private String address;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "age")
    private int age;
    public static final String FIND_ALL = "Patient.findAll";

    @Column(name = "gender")
    private char gender;

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patient_Id + '\'' +
                ", patientName='" + patient_Name + '\'' +
                ", patientSurname='" + patient_Surname + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public Patient() {
    }

    public Patient(String patientName, String patientSurname, String address, String country, String city, int age, char gender) {
        this.patient_Name = patientName;
        this.patient_Surname = patientSurname;
        this.address = address;
        this.country = country;
        this.city = city;
        this.age = age;
        this.gender = gender;
    }

    public String getPatientId() {
        return patient_Id;
    }

    public void setPatientId(String patientId) {
        this.patient_Id = patientId;
    }

    public String getPatientName() {
        return patient_Name;
    }

    public void setPatientName(String patientName) {
        this.patient_Name = patientName;
    }

    public String getPatientSurname() {
        return patient_Surname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patient_Surname = patientSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
