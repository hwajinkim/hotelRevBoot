package com.boot.sonorous.admin.dto;

import com.boot.sonorous.admin.entity.RoomImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    int status;
    Page<T> list;
    T data;
    List<RoomImage> roomImages;
}
