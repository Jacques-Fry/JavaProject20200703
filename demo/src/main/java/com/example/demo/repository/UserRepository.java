package com.example.demo.repository;


import com.example.demo.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 查询用户信息
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);

    /**
     * 用户账号密码是否正确
     * @param username
     * @param password
     * @return
     */
    boolean existsByUsernameAndPassword(String username,String password);

    /**
     * 根据ID获取数据
     * @param id
     * @return
     */
    boolean existsById(Long id);

    /**
     * 修改数据
     * @param id
     * @param cphone
     * @param cunitname
     * @return
     */
    @Modifying
    @Query(value="update huihe56_user set cphone=?2 ,cUnitName=?3 where id=?1",nativeQuery=true)
    int updateUser(Long id,String cphone,String cunitname);

    Page<User> findByCunitnameLike(String name,Pageable pageable);
}
