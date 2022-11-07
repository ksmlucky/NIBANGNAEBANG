package com.footprints.businessservice.app.domain.message.service;

import com.footprints.businessservice.app.domain.message.dto.MessageDto;
import com.footprints.businessservice.app.domain.message.dto.MessageRequest;
import com.footprints.businessservice.app.domain.message.entity.Message;
import com.footprints.businessservice.app.domain.message.exception.MessageException;
import com.footprints.businessservice.app.domain.message.exception.MessageExceptionType;
import com.footprints.businessservice.app.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public List<MessageDto> getAllReceivedMessages(String receiveMember, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllReceivedMessages(Long.parseLong(receiveMember), pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void sendMessage(String sendMember, MessageRequest request) {
        Message message = Message.builder()
                .sendMember(Long.parseLong(sendMember))
                .receiveMember(request.getReceiveMember())
                .title(request.getTitle())
                .content(request.getContent())
                .boardClassification(request.getBoardClassification())
                .build();

        messageRepository.save(message);
    }

    // 받은 쪽지 삭제
    @Override
    @Transactional
    public void deleteMessageByReceiveMember(String receiveMemberId, Long messageId) {
        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        // 받은 사람이랑 받은 사람 아이디(receiveMemberId)가 같으면
        if (message.getReceiveMember() == Long.parseLong(receiveMemberId)) {
            // 받은 사람이 삭제 체크
            message.deleteByReceiveMember();
        } else {
            throw new MessageException(MessageExceptionType.NO_AUTHORIZATION_MESSAGE);
        }

        // 보낸 사람도 삭제했으면 아예 삭제
        if (message.isDeletedBySendMember()) {
            messageRepository.delete(message);
        }
    }

    @Override
    public List<MessageDto> getAllSentMessages(String sendMember, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllSentMessages(Long.parseLong(sendMember), pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    // 보낸 편지 삭제
    @Override
    @Transactional
    public void deleteMessageBySendMember(String sendMemberId, Long messageId) {
        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        // 보낸 사람이랑 보낸 사람 아이디(sendMemberId)가 같으면
        if (message.getSendMember() == Long.parseLong(sendMemberId)) {
            // 보낸 사람이 삭제 체크
            message.deleteBySendMember();
        } else {
            throw new MessageException(MessageExceptionType.NO_AUTHORIZATION_MESSAGE);
        }


        // 받은 사람도 삭제했으면 아예 삭제
        if (message.isDeletedByReceiveMember()) {
            messageRepository.delete(message);
        }
    }

    @Override
    @Transactional
    public MessageDto getMessage(String memberId, Long messageId) {
        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        if (Long.parseLong(memberId) == message.getSendMember() || Long.parseLong(memberId) == message.getReceiveMember()) {
            message.readCheck();
            return new MessageDto(message);
        } else {
            throw new MessageException(MessageExceptionType.NO_AUTHORIZATION_MESSAGE);
        }
    }
}
