package com.example.guestbook.repository;

import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {
    @Autowired
    private GuestBookRepository guestBookRepository;


    @Test
    public void insertDumies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            GuestBook guestBook = GuestBook.builder()
                    .title("Title........" + i)
                    .content("content..." + i)
                    .writer("user" + (i / 10))
                    .build();
            System.out.println(guestBookRepository.save(guestBook));
        });
    }

    @Test
    public void updaeTest(){
        Optional<GuestBook> result = guestBookRepository.findById(300l);

        if(result.isPresent()){
            GuestBook guestBook = result.get();

            guestBook.changeTitle("change Title...");
            guestBook.changeContent("change content");

            guestBookRepository.save(guestBook);
        }
    }

    //Querydsl 테스트
    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;
        String keyword ="1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestBookRepository.findAll(builder,pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });


    }
}
