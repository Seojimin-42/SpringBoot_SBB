package com.mysite.sbb.study.repository;

import com.mysite.sbb.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
