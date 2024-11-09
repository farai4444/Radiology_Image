package farai.xray_image_manager.User;

import farai.xray_image_manager.Patient.JPARepo;
import farai.xray_image_manager.Patient.Patient;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<Sys_user,Integer> {
    @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM Sys_user u WHERE u.name=:#{#user.name} or u.email=:#{#user.email} or u.surname=:#{#user.surname} or u.username=:#{#user.username} or u.password=:#{#user.password}")
     boolean existsByObject(@Param("user") Sys_user user);
}
