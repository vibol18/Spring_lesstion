package com.example.real_school.communication.service.impl;

import com.example.real_school.communication.dto.NoticeDto;
import com.example.real_school.communication.entity.Notice;
import com.example.real_school.communication.repository.NoticeRepository;
import com.example.real_school.communication.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDto> getAllNotices() {
        return noticeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public NoticeDto getNoticeById(Long id) {
        return mapToDto(noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id)));
    }

    @Override
    public NoticeDto createNotice(NoticeDto dto) {
        Notice notice = new Notice();
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setAuthor(dto.getAuthor());
        notice.setPublishedAt(LocalDateTime.now());
        notice.setIsActive(true);
        return mapToDto(noticeRepository.save(notice));
    }

    @Override
    public NoticeDto updateNotice(Long id, NoticeDto dto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id));
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        if (dto.getIsActive() != null) notice.setIsActive(dto.getIsActive());
        return mapToDto(noticeRepository.save(notice));
    }

    @Override
    public void deleteNotice(Long id) {
        if (!noticeRepository.existsById(id)) throw new RuntimeException("Notice not found with id: " + id);
        noticeRepository.deleteById(id);
    }

    private NoticeDto mapToDto(Notice n) {
        NoticeDto dto = new NoticeDto();
        dto.setId(n.getId());
        dto.setTitle(n.getTitle());
        dto.setContent(n.getContent());
        dto.setAuthor(n.getAuthor());
        dto.setPublishedAt(n.getPublishedAt());
        dto.setIsActive(n.getIsActive());
        return dto;
    }
}
