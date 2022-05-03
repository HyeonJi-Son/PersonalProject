package com.example.demo.service.boardComment;

import com.example.demo.controller.boardCommentController.request.BoardCommentRequest;
import com.example.demo.entity.boardComment.BoardComment;
import com.example.demo.entity.booking.BookingInfo;
import com.example.demo.repository.boardComment.BoardCommentRepository;
import com.example.demo.repository.booking.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.valueOf;

@Slf4j
@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    BoardCommentRepository repository;

    @Autowired
    BookingRepository bookingRepository;

    @Transactional
    @Override
    public void includeCommentRegister(BoardCommentRequest info, String originalFilename) {
        String putLink = info.getBookingNo()+"."+info.getId()+"."+originalFilename;

        Optional<BookingInfo> bookingInfo = bookingRepository.findById(info.getBookingNo());
        BookingInfo bookingInfo1 = bookingInfo.get();

        Long findNoParent = repository.findMax(info.getBookingNo());
        Long findNoChild = 0l;

        if(findNoParent == null) {
            findNoParent = 1l;
        }else {
            findNoParent +=1l;
        }

        BoardComment boardComment = new BoardComment(info.getId(),info.getComments(),putLink,findNoParent, findNoChild, bookingInfo1 );
        repository.save(boardComment);
    }

    @Transactional
    @Override
    public void exceptFileCommentRegister(BoardCommentRequest info) {
        Optional<BookingInfo> bookingInfo = bookingRepository.findById(info.getBookingNo());
        BookingInfo bookingInfo2 = bookingInfo.get();
        log.info("bookingInfo2 :"+bookingInfo2);

        Long findNoParent = repository.findMax(info.getBookingNo());
        Long findNoChild = 0l;

        if(findNoParent == null) {
            findNoParent = 1l;
        }else {
            findNoParent +=1l;
        }

        BoardComment boardComment2 = new BoardComment(info.getId(),info.getComments(), findNoParent, findNoChild, bookingInfo2);
        repository.save(boardComment2);

    }

    @Transactional
    @Override
    public List<BoardComment> list(Integer bookingNo) {
        return repository.findBookingNoLost(Long.valueOf(bookingNo));
    }

    @Override
    public void includeCoCommentRegister(BoardCommentRequest info, String originalFilename) {
        String putLink = info.getBookingNo()+"."+info.getId()+"."+originalFilename;

        Optional<BookingInfo> bookingInfo = bookingRepository.findById(info.getBookingNo());
        BookingInfo bookingInfo1 = bookingInfo.get();

        Long findNoParent = info.getParentNo();
        Long findNoChild = repository.findMaxParent(findNoParent);

        findNoChild+=1l;

        BoardComment boardComment = new BoardComment(info.getId(),info.getComments(),putLink,findNoParent, findNoChild, bookingInfo1 );
        repository.save(boardComment);
    }

    @Override
    public void exceptFileCoCommentRegister(BoardCommentRequest info) {
        Optional<BookingInfo> bookingInfo = bookingRepository.findById(info.getBookingNo());
        BookingInfo bookingInfo2 = bookingInfo.get();
        log.info("bookingInfo2 :"+bookingInfo2);

        Long findNoParent = info.getParentNo();
        Long findNoChild = repository.findMaxParent(findNoParent);

        findNoChild+=1l;

        BoardComment boardComment2 = new BoardComment(info.getId(),info.getComments(), findNoParent, findNoChild, bookingInfo2);
        repository.save(boardComment2);

    }
}
