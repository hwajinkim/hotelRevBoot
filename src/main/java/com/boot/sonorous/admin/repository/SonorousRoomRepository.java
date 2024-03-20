package com.boot.sonorous.admin.repository;

import com.boot.sonorous.admin.entity.Room;
import com.boot.sonorous.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SonorousRoomRepository extends JpaRepository<Room, Integer> {
}
