package com.soromiso.studentsys.model.listener;

import java.time.LocalDateTime;

public interface Iauditable {
  LocalDateTime getCreatedAt();
  LocalDateTime getUpdatedAt();

  void setCreatedAt(LocalDateTime createdAt);
  void setUpdatedAt(LocalDateTime updatedAt);
}
