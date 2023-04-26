package com.thebestgroup.teamquest.repository;

import com.thebestgroup.teamquest.model.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QuestRepository extends JpaRepository<Quest, Long>, QuerydslPredicateExecutor<Quest> {
}