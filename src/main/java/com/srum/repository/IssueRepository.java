package com.srum.repository;

import com.srum.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("From Issue i where i.clazz.id = ?1")
    List<Issue> findAllIssuesByClazzId(Long clazzid);
}
