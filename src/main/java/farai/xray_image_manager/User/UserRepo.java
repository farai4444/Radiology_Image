package farai.xray_image_manager.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<Sys_user,Integer> {
    @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM Sys_user u WHERE u.name=:#{#user.name} and u.surname=:#{#user.surname} and u.email=:#{#user.email} and u.username=:#{#user.username} AND u.password=:#{#user.password}")
     boolean existsByObject(@Param("user") Sys_user user);
    @Query("SELECT CASE WHEN COUNT(u)>0 THEN u.password END FROM Sys_user u WHERE u.username=:#{#user.username}")
    String existsByObject1(@Param("user") Sys_user user);
    @Query("SELECT u FROM Sys_user u WHERE username=?1")
    Sys_user findByUserName(String username);
}
