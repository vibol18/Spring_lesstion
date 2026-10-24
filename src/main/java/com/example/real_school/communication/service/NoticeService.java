package com.example.real_school.communication.service;

import com.example.real_school.communication.dto.NoticeDto;
import java.util.List;

public interface NoticeService {
    List<NoticeDto> getAllNotices();
    NoticeDto getNoticeById(Long id);
    NoticeDto createNotice(NoticeDto dto);
    NoticeDto updateNotice(Long id, NoticeDto dto);
    void deleteNotice(Long id);
}
