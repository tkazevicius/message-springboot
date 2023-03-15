package com.organization.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageControllerTests {

    public static final String MESSAGE = "yo yo";
    @Mock
    private MessageRepository repository;

    @InjectMocks
    private MessageController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldBEandDBWork() {
        when(repository.findAll()).thenReturn(List.of(new Message(MESSAGE)));
        assertEquals(MessageController.BE_SUCCESS_MESSAGE + System.lineSeparator() + MessageController.DB_SUCCESS_MESSAGE + System.lineSeparator() + MessageController.STORED_MESSAGES + System.lineSeparator() + MESSAGE, controller.getMessage());
    }
}
