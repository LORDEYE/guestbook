package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDTO;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepository repository; //반드시 final로 선언

    @Override
    public Long register(GuestBookDTO dto) {
        log.info("DTO.............");
        log.info(dto);

        GuestBook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }
}
