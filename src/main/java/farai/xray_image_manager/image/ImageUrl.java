package farai.xray_image_manager.image;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "imageurl")
public class ImageUrl implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "url_Id")
   public int url_Id;
    @Column(name = "patient_id")
   public int patient_Id;

    @Column(name = "uploader")
   public String uploader;
    @Column(name = "upload_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   public LocalDateTime upload_Date;

    @Column(name = "url")
   public String url;

    public ImageUrl() {}

    public ImageUrl(int patient_Id, String uploader, LocalDateTime upload_Date, String url) {
        this.patient_Id = patient_Id;
        this.uploader = uploader;
        this.upload_Date = upload_Date;
        this.url = url;
    }

    public int getUrl_Id() {
        return url_Id;
    }

    public void setUrl_Id(int url_Id) {
        this.url_Id = url_Id;
    }

    public int getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(int patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public LocalDateTime getUpload_Date() {
        return upload_Date;
    }

    public void setUpload_Date(LocalDateTime upload_Date) {
        this.upload_Date = upload_Date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageUrl{" +
                "url_Id=" + url_Id +
                ", patient_Id=" + patient_Id +
                ", uploader='" + uploader + '\'' +
                ", upload_Date=" + upload_Date +
                ", url='" + url + '\'' +
                '}';
    }

}
