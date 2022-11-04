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
    public List<MessageDto> getAllReceivedMessages(String sendMember, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllMessagesBySendMember(Long.parseLong(sendMember), pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void sendMessage(String sendMember, MessageRequest request) {
        // receiveMember 검증 -> 존재하지 않는 수신자면 예외 처리

        Message message = Message.builder()
                .sendMember(Long.parseLong(sendMember))
                .receiveMember(request.getReceiveMember())
                .title(request.getTitle())
                .content(request.getContent())
                .boardClassification(request.getBoardClassification())
                .build();

        messageRepository.save(message);
    }

    @Override
    public void deleteMessageByReceiveMember(Long receiveMemberId, Long messageId) {

    }

    @Override
    public List<MessageDto> getAllSentMessages(String receiveMember, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllMessagesByReceiveMember(Long.parseLong(receiveMember), pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void deleteMessageBySendMember(Long sendMemberId, Long messageId) {

    }

    @Override
    @Transactional
    public MessageDto getMessage(Long messageId) {
        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        message.readCheck();

        return new MessageDto(message);
    }
}
