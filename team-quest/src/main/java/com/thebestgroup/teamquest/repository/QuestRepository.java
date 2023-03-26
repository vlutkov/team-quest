package com.thebestgroup.teamquest.repository;

import com.thebestgroup.teamquest.model.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
