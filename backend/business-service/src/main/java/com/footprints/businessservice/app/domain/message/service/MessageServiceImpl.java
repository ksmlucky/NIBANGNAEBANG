package com.footprints.businessservice.app.domain.message.service;

import com.footprints.businessservice.app.domain.member.MemberServiceClient;
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
    private final MemberServiceClient memberServiceClient;

    @Override
    public List<MessageDto> getAllReceivedMessages(String receiveMember, Pageable pageable) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(receiveMember)).getNickname();

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllReceivedMessages(nickname, pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void sendMessage(String sendMember, MessageRequest request) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(sendMember)).getNickname();

        Message message = Message.builder()
                .sendMember(nickname)
                .receiveMember(request.getReceiveMember())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        messageRepository.save(message);
    }

    // 받은 쪽지 삭제
    @Override
    @Transactional
    public void deleteMessageByReceiveMember(String receiveMemberId, Long messageId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(receiveMemberId)).getNickname();

        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        // 받은 사람이랑 받은 사람 아이디(receiveMemberId)가 같으면
        if (message.getReceiveMember().equals(nickname)) {
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
        String nickname = memberServiceClient.selectNickname(Long.parseLong(sendMember)).getNickname();

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Message> messages = messageRepository.getAllSentMessages(nickname, pageRequest);

        List<MessageDto> result = messages.stream()
                .map(message -> new MessageDto(message))
                .collect(Collectors.toList());

        return result;
    }

    // 보낸 편지 삭제
    @Override
    @Transactional
    public void deleteMessageBySendMember(String sendMemberId, Long messageId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(sendMemberId)).getNickname();

        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        // 보낸 사람이랑 보낸 사람 아이디(sendMemberId)가 같으면
        if (message.getSendMember().equals(nickname)) {
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
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        Message message = messageRepository.getMessage(messageId);

        if (message == null) {
            throw new MessageException(MessageExceptionType.NOT_FOUND_MESSAGE);
        }

        // 권한이 있으면
        if (nickname.equals(message.getSendMember()) || nickname.equals(message.getReceiveMember())) {
            if (nickname.equals(message.getReceiveMember())) {
                message.readCheck();
            }

            return new MessageDto(message);
        } else {
            throw new MessageException(MessageExceptionType.NO_AUTHORIZATION_MESSAGE);
        }
    }
}
