package farai.xray_image_manager.Patient;

import jakarta.persistence.*;

import java.io.Serializable;
import static jakarta.persistence.GenerationType.IDENTITY;


@NamedQueries({
        @NamedQuery(name= Patient.FIND_ALL, query="UPDATE Patient SET age = 25, gender = 'm', country = 'Zimbabwe', city = 'Chinhoyi', address = 'Gunhill 3404' WHERE patientId = '1' ")})
@SqlResultSetMapping(
        name="patientResult",
        entities=@EntityResult(entityClass = Patient.class)
)
@Entity
@Table(name = "Patient")
public class Patient implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "patientId")
    private String patientId;
    @Column(name = "pname")
    private String pname;
    @Column(name = "psurname")
    private String psurname;
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
                "patientId='" + patientId + '\'' +
                ", patientName='" + pname + '\'' +
                ", patientSurname='" + psurname + '\'' +
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
        this.pname = patientName;
        this.psurname = patientSurname;
        this.address = address;
        this.country = country;
        this.city = city;
        this.age = age;
        this.gender = gender;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return pname;
    }

    public void setPatientName(String patientName) {
        this.pname = patientName;
    }

    public String getPatientSurname() {
        return psurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.psurname = patientSurname;
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
