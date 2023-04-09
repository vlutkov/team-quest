package com.thebestgroup.teamquest.repository;

import com.thebestgroup.teamquest.model.entity.StorageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepository extends JpaRepository<StorageFile, Long> {
}
