package com.example.happyre.repository;

import com.example.happyre.entity.DiaryEntity;
import com.example.happyre.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {

    @Query("SELECT d FROM DiaryEntity d WHERE FUNCTION('DATE', d.date) = :currentDate AND d.userEntity = :userEntity")
    List<DiaryEntity> findByUserEntityAndDate(@Param("userEntity") UserEntity userEntity, @Param("currentDate") Date currentDate);

    List<DiaryEntity> findByUserEntity(UserEntity userEntity);

    @Query("SELECT d FROM DiaryEntity d WHERE d.userEntity = :userEntity AND d.date > :startDate AND d.date <= :endDate")
    List<DiaryEntity> findByUserEntityAndDateRange(@Param("userEntity") UserEntity userEntity, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
