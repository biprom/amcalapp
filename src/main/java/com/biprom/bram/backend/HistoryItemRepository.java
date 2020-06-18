package com.biprom.bram.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biprom.bram.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
